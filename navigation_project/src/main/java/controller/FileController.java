package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileUpload")
//@MultipartConfig
//public class FileController extends HttpServlet
//{
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Handles file upload requests.
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		try
//		{
//			// Retrieve allowed extensions from config
//			String allowedImageExtensions = Config.getProperty("ALLOWED_IMAGE_EXCETIONS[]");
//			String allowedDocumentExtensions = Config.getProperty("ALLOWED_DOCUMENT_EXCETIONS[]");
//
//			// Get file details
//			Part filePart = request.getPart("file");
//			if(filePart == null || filePart.getSubmittedFileName() == null)
//			{
//				sendErrorResponse(response, "No file uploaded.");
//				return;
//			}
//
//			String filename = filePart.getSubmittedFileName();
//			String fileExtension = getFileExtension(filename);
//
//			System.out.println("fileExtension:" + fileExtension);
//
//			// Check for valid extensions
//			if(!isValidExtension(fileExtension, allowedImageExtensions, allowedDocumentExtensions))
//			{
//				sendErrorResponse(response, "Invalid file extension.");
//				return;
//			}
//
//			// Generate folder name and handle file upload
//			String folderName = "uploads"; // Example folder name, can be dynamic as per your logic
//			String randomFilename = UtilityClass.generateRandomNumber(9) + "_" + filename;
//			String uploadDirectory = createUploadDirectory(folderName);
//			String uploadedFilePath = uploadDirectory + File.separator + randomFilename;
//
//			// Write file to disk
//			if(!writeToFile(filePart.getInputStream(), uploadedFilePath))
//			{
//				sendErrorResponse(response, "File size exceeded.");
//				return;
//			}
//
//			// Create thumbnail if it's an image
//			if(isImageFile(fileExtension))
//			{
//				createThumbnail(uploadedFilePath);
//			}
//
//			// Return success response
//			sendSuccessResponse(response, "File uploaded successfully.");
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			sendErrorResponse(response, "An error occurred: " + e.getMessage());
//		}
//	}
//
//	/**
//	 * Extracts the file extension from the filename.
//	 */
//	private String getFileExtension(String filename)
//	{
//		int lastIndex = filename.lastIndexOf(".");
//		return (lastIndex != -1) ? filename.substring(lastIndex + 1).toLowerCase() : "";
//	}
//
//	/**
//	 * Checks if the file extension is valid for the upload type.
//	 */
//	private boolean isValidExtension(String fileExtension, String allowedImageExtensions,
//			String allowedDocumentExtensions)
//	{
//		return Arrays.asList(allowedImageExtensions.split(",")).contains(fileExtension)
//				|| Arrays.asList(allowedDocumentExtensions.split(",")).contains(fileExtension);
//	}
//
//	/**
//	 * Creates the upload directory if it does not exist.
//	 */
//	private String createUploadDirectory(String folderName)
//	{
//		String projectPath = Config.getProperty("PROJECT_PATH");
//		String uploadDirectory = projectPath + File.separator + folderName;
//		File folder = new File(uploadDirectory);
//		if(!folder.exists())
//		{
//			folder.mkdirs();
//		}
//		return uploadDirectory;
//	}
//
//	/**
//	 * Writes the uploaded file to the specified location.
//	 */
//	private boolean writeToFile(InputStream inputStream, String filePath) throws IOException
//	{
//		try(OutputStream outputStream = new FileOutputStream(filePath))
//		{
//			byte[] buffer = new byte[1024];
//			int bytesRead;
//			while((bytesRead = inputStream.read(buffer)) != -1)
//			{
//				outputStream.write(buffer, 0, bytesRead);
//			}
//		}
//
//		File uploadedFile = new File(filePath);
//		return uploadedFile.length() <= Integer.parseInt(Config.getProperty("MAXIMUM_DOCUMENT_SIZE"));
//	}
//
//	/**
//	 * Checks if the file is an image based on its extension.
//	 */
//	private boolean isImageFile(String fileExtension)
//	{
//		return Arrays.asList(Config.getProperty("ALLOWED_IMAGE_EXCETIONS[]").split(",")).contains(fileExtension);
//	}
//
//	/**
//	 * Creates a thumbnail for the uploaded image.
//	 */
//	private void createThumbnail(String filePath) throws IOException
//	{
//		String thumbnailPath = filePath.replaceFirst("\\.[^.]+$", "_thumbnail.jpg");
//		int thumbnailWidth = Integer.parseInt(Config.getProperty("THUMBNAIL_WIDTH"));
//		int thumbnailHeight = Integer.parseInt(Config.getProperty("THUMBNAIL_HEIGHT"));
//		Thumbnails.of(new File(filePath)).size(thumbnailWidth, thumbnailHeight).toFile(thumbnailPath);
//	}
//
//	/**
//	 * Sends an error response to the client.
//	 */
//	private void sendErrorResponse(HttpServletResponse response, String message) throws IOException
//	{
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		response.getWriter().write(new ResponseWrapper<>(0, 400, message).toString());
//	}
//
//	/**
//	 * Sends a success response to the client.
//	 */
//	private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException
//	{
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.getWriter().write(new ResponseWrapper<>(1, 200, message).toString());
//	}
//}

//package controller;
//
//import java.awt.image.BufferedImage;
//import java.io.*;
//import javax.imageio.ImageIO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import net.coobird.thumbnailator.Thumbnails;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class FileController extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Specify the absolute upload path
		String uploadPath = "C:\\Users\\Lovepreet singh\\git\\repository\\navigation_project\\src\\main\\webapp\\img";

		// Create the upload directory if it doesn't exist
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists())
		{
			uploadDir.mkdirs(); // Ensure the directory structure is created
		}

		String fileName = "";
		for(Part part : request.getParts())
		{
			// Get the file name from the part
			fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			if(fileName != null && !fileName.isEmpty())
			{
				// Save the file to the specified absolute path
				part.write(new File(uploadDir, fileName).getAbsolutePath());
			}
		}

		// Respond to the client
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter())
		{
			out.println("<html><body>");
			out.println("<h2>File uploaded successfully!</h2>");
			out.println("<p>Uploaded file: " + fileName + "</p>");
			out.println("<p>Saved at: " + uploadPath + File.separator + fileName + "</p>");
			out.println("</body></html>");
		}
	}
}
