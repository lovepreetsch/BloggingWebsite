<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head> 
<title>File Upload</title> 
</head> 
<body>

<form method="post" action="FileUpload" enctype="multipart/form-data">
    <!-- Add the type field -->
    <input type="hidden" name="type" value="BLOG" />
    
    <!-- File input -->
    <input type="file" name="file" />
    
    <!-- Submit button -->
    <input type="submit" value="Upload" />
</form>

</body>
</html>