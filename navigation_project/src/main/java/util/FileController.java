package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.ResponseWrapper;
import net.coobird.thumbnailator.Thumbnails;

//@Path("/api")
public class FileController
{

	@SuppressWarnings({ "unchecked" })
	public ResponseWrapper<?> uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("type") String type,
			@FormDataParam("previousFileName") String previousFileName, @FormDataParam("id") String id)
	{
		try
		{

			// Retrieve the property and split it into an array
			String extensions = Constant.ALLOWED_IMAGE_AND_DOCUMENT_EXCETIONS;
			String[] allowedImageAndDocumentExtensions = extensions.split(",");

			extensions = Constant.ALLOWED_IMAGE_EXCETIONS;
			String[] allowedImageExtensions = extensions.split(",");

			extensions = Constant.ALLOWED_DOCUMENT_EXCETIONS;
			String[] allowedDocumentExtensions = extensions.split(",");

			System.out.println("type: " + type);
			System.out.println("previous image: " + previousFileName);
			System.out.println("id: " + id);

			boolean isUploadingDocument = false;
			boolean isUploadingImage = false;

			// Check if the uploaded file is an image file
			//        String allowedExtensions[] = {"jpg", "jpeg", "png"};
			String filename = fileDetail.getFileName();
			String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
			boolean isImageFile = false;
			String tableName = "";
			String imageColumnName = "";
			String uniqueKey = "";

			switch(type.toUpperCase()) {
			case "BLOG":
				type = "blog";
				isUploadingImage = true;
				tableName = "blog";
				imageColumnName = "image";
				uniqueKey = "id";
				break;
			}

			if(isUploadingImage && isUploadingDocument)
			{
				for(String extension : allowedImageAndDocumentExtensions)
				{
					if(extension.equals(fileExtension))
					{
						isImageFile = true;
						break;
					}
				}

				if(!isImageFile)
				{

					return new ResponseWrapper<String>(	0,
														403,
														"Upload "
																+ Arrays.toString(allowedImageAndDocumentExtensions)
																+ " file only");
				}
			}
			else if(isUploadingImage)
			{
				for(String extension : allowedImageExtensions)
				{
					if(extension.equals(fileExtension))
					{
						isImageFile = true;
						break;
					}
				}
				if(!isImageFile)
				{

					return new ResponseWrapper<String>(	0,
														404,
														"Upload "
																+ Arrays.toString(allowedImageExtensions)
																+ " image only");
				}
			}

			else if(isUploadingDocument)
			{
				boolean isPdfFile = false;
				if(type.equalsIgnoreCase("TESTREPORT"))
				{
					for(String extension : allowedImageAndDocumentExtensions)
					{
						if(extension.equals(fileExtension))
						{
							isPdfFile = true;
							break;
						}
					}
				}
				else
				{
					for(String extension : allowedDocumentExtensions)
					{
						if(extension.equals(fileExtension))
						{
							isPdfFile = true;
							break;
						}
					}
				}

				if(!isPdfFile)
				{
					return new ResponseWrapper<String>(0, 403, "Upload PDF only");
				}
			}

			// Set the file type and folder
			String folderName = type;
			if(isUploadingImage && !isUploadingDocument)
			{
				folderName = type + "Pictures";
			}
			else if(isUploadingDocument)
			{
				folderName = type;
			}
			String randomFilename = UtilityClass.generateRandomNumber(9) + "_" + filename;

			String projectPath = Constant.PROJECT_PATH;

			String newFolderPath = projectPath + Constant.NAVIGATION_PROJECT_FOLDER_PATH + folderName; // currentProjectPath + "/" + folderName;
			File folder = new File(newFolderPath);

			System.out.println("newFolderPath: " + newFolderPath);

			String uploadDirectory = newFolderPath + "/" + randomFilename;

			if(!folder.exists())
			{
				boolean isFolderCreated = folder.mkdirs();
				if(isFolderCreated)
				{
					System.out.println("Folder created successfully.");
				}
				else
				{
					System.out.println("Failed to create folder.");
				}
			}
			else
			{
				System.out.println("Folder already exists.");
			}

			System.out.println(uploadDirectory);

			// Write InputStream to a temporary file (for main image upload)
			File tempFile = File.createTempFile("upload_", filename);
			int writeToFileResult = writeToFile(uploadedInputStream,
												tempFile.getPath(),
												isUploadingImage,
												isUploadingDocument);

			// check File Size
			if(writeToFileResult == -1) // file allowed upto 5MB
			{
				tempFile.delete();

				return new ResponseWrapper<String>(0, 403, "Maximum size should be 5MB");
			}

			String thumbnailFileUrl = "";
			if(isUploadingImage && !isUploadingDocument)
			{
				// Create a thumbnail (resize the image)
				System.out.println("thumbanail file name: " + filename);
				File thumbnailFile = File.createTempFile("thumbnail_", filename);
				int writeToFileThumbnailResult = writeToFile(	uploadedInputStream,
																thumbnailFile.getPath(),
																isUploadingImage,
																isUploadingDocument);
				int thumbnailWidth = Constant.THUMBNAIL_WIDTH;
				int thumbnailHeight = Constant.THUMBNAIL_HEIGHT;

				Thumbnails.of(tempFile).size(thumbnailWidth, thumbnailHeight).toFile(thumbnailFile);

				//        	try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e1) {e1.printStackTrace();} 

			}
			return new ResponseWrapper<>(1, 200, "File Uploaded Successfully");
		}
		catch(

		Exception e)
		{
			e.printStackTrace();

			return new ResponseWrapper<>(	-1,
											"An error occurred: " + e.getMessage(),
											500,
											"Error while uploading the file.");

		}
	}

	private static int writeToFile(InputStream uploadedInputStream, String uploadedFileLocation,
			boolean isUploadingImage, boolean isUploadingDocument) throws IOException
	{
		OutputStream out = new FileOutputStream(uploadedFileLocation);
		int read = 0;
		byte buffer[] = new byte[1024];
		while((read = uploadedInputStream.read(buffer)) != -1)
		{
			//LoggerUtil.log( Arrays.toString(buffer) );
			out.write(buffer, 0, read);
		}
		out.flush();
		out.close();

		//        writeResult = true;

		//check File Size
		File uploadedFile = new File(uploadedFileLocation);
		if(uploadedFile.exists()	&& (!isUploadingImage && !isUploadingDocument)
			&& ((isUploadingImage && uploadedFile.length() > Constant.MAXIMUM_IMAGE_SIZE)
				|| (isUploadingDocument && uploadedFile.length() > Constant.MAXIMUM_DOCUMENT_SIZE))) //file allowed upto 5MB
		{
			System.out.println("File Size Exceeded");
			uploadedFile.delete();
			//        	writeResult = false;

			return -1;
		}

		return 1;
	}
}