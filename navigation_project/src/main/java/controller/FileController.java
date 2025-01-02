package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.FilenameUtils; // Optional, for utility methods like extracting file extensions
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import beans.ResponseWrapper;
import util.UtilityClass;

@WebServlet("/FileUpload")
public class FileController extends HttpServlet
{

	private Boolean writeResult;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("application/json");
		try
		{
			if(!ServletFileUpload.isMultipartContent(request))
			{
				response.getWriter()
						.write(new ResponseWrapper<>(0, 400, "Invalid request, content is not multipart").toString());
				return;
			}

			System.out.println("111111111111111111111");

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024 * 5); // 5MB threshold

			// Configure a repository (to ensure a secure temp location is used)
			File tempDir = new File(System.getProperty("java.io.tmpdir"));
			factory.setRepository(tempDir);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(100 * 1024 * 1024); // 100MB max

			// Parse the request
			List<FileItem> formItems = upload.parseRequest(request);
			System.out.println("22222222222222222222222222");

			InputStream uploadedInputStream = null;
			String fileName = null;
			String type = null;

			for(FileItem item : formItems)
			{
				System.out.println("3333333333333333333333333333");
				if(item.isFormField())
				{
					// Process regular form field
					if("type".equals(item.getFieldName()))
					{
						type = item.getString();
					}
				}
				else
				{
					// Process file field
					fileName = item.getName();
					uploadedInputStream = item.getInputStream();
				}
			}

			System.out.println("44444444444444444444444444");
			if(uploadedInputStream == null || fileName == null || type == null)
			{
				System.out.println("55555555555555555555555555555");
				response.getWriter().write(new ResponseWrapper<>(0, 400, "Missing required fields").toString());
				return;
			}
			System.out.println("666666666666666666666666666666");

			// Simulate FormDataContentDisposition
			FormDataContentDisposition fileDetail = FormDataContentDisposition.name("file").fileName(fileName).build();

			// Call the uploadFile method
			ResponseWrapper<?> result = uploadFile(uploadedInputStream, fileDetail, type);
			response.getWriter().write(result.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.getWriter()
					.write(new ResponseWrapper<>(	-1,
													"An error occurred: " + e.getMessage(),
													500,
													"Error while processing the file upload")
							.toString());
		}
	}

	@SuppressWarnings({ "unchecked" })
	private ResponseWrapper<?> uploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail,
			String type)
	{
		try
		{
			boolean isUploadingDocument = false;
			boolean isUploadingImage = false;
			boolean isUploadingVideo = false;

			// Check if the uploaded file is an image file       
			String filename = fileDetail.getFileName();
			String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
			boolean isImageFile = false;
			String tableName = "";
			String imageColumnName = "";
			String uniqueKey = "";
			final String allowedVideoExtensions[] = { "mp4", "mkv" };
			final String allowedImageExtensions[] = { "png", "jpg", "jpeg" };
			final String allowedDocumentExtensions[] = { "pdf" };
			final String allowedImageAndDocumentExtensions[] = { "png", "jpg", "jpeg", "pdf" };

			switch(type.toUpperCase()) {
			case "BLOG":
				type = "blog";
				isUploadingImage = true;
				isUploadingVideo = true;
				isUploadingDocument = true;
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
					return new ResponseWrapper<>(	0,
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
					return new ResponseWrapper<>(	0,
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
					return new ResponseWrapper<>(0, 403, "Upload PDF only");
				}
			}
			else if(isUploadingVideo)
			{
				boolean isVideoFile = false;
				for(String extension : allowedVideoExtensions)
				{
					if(extension.equals(fileExtension))
					{
						isVideoFile = true;
						break;
					}
				}
				if(!isVideoFile)
				{
					return new ResponseWrapper<>(	0,
													404,
													"Upload "
															+ Arrays.toString(allowedVideoExtensions)
															+ " Video only");
				}
			}

			String folderName = type;
			//			C:\Users\Lovepreet singh\git\repository\navigation_project\src\main\webapp
			String projectPath = "C:\\Users\\Lovepreet singh\\git\\repository\\navigation_project";
			System.out.println("projectPath: " + projectPath);

			String newFolderPath = projectPath + "\\src\\main\\webapp\\img\\" + folderName;
			File folder = new File(newFolderPath);

			System.out.println("newFolderPath: " + newFolderPath);

			String randomFilename = UtilityClass.generateRandomNumber(9) + "_" + filename;

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

			// Write InputStream to a temporary file
			File tempFile = File.createTempFile("upload_", filename);
			writeToFile(uploadedInputStream, tempFile.getPath());

			// Writing File to Disk
			writeResult = writeToFile(uploadedInputStream, uploadDirectory);
			if(!writeResult)
			{
				tempFile.delete();
				return new ResponseWrapper<>(0, 403, "Maximum size should be 50MB");
			}

			if(writeResult)
			{
				try
				{
					TimeUnit.SECONDS.sleep(5);
				}
				catch(InterruptedException e1)
				{
					e1.printStackTrace();
				}
				return new ResponseWrapper<>(1, writeResult, 200, "File Uploaded Successfully");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseWrapper<>(	-1,
											"An error occurred: " + e.getMessage(),
											500,
											"Error while uploading the file.");
		}
		return new ResponseWrapper<>(-1, "An error occurred: ", 500, "Error while uploading the file.");
	}

	private Boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException
	{
		OutputStream out = new FileOutputStream(uploadedFileLocation);
		int read;
		byte[] buffer = new byte[1024];
		while((read = uploadedInputStream.read(buffer)) != -1)
		{
			out.write(buffer, 0, read);
		}
		out.flush();
		out.close();

		writeResult = true;

		// Check File Size
		File uploadedFile = new File(uploadedFileLocation);
		if(uploadedFile.exists() && uploadedFile.length() > 100 * 1024 * 1024)
		{ // File allowed up to 100MB
			System.out.println("File Size Exceeded");
			uploadedFile.delete();
			writeResult = false;
		}

		return writeResult;
	}
}
