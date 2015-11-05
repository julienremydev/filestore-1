<html>
	<head>
		<title>FileStore Index page</title>
	</head>
	<body>
		<h1>Post a File</h1>
		<form action="./api/files" method="post" enctype="multipart/form-data">
			Your email: <input type="text" name="owner"/><br/>
			Receiver email: <input type="text" name="receivers"/><br/>
			Your file: <input type="file" name="file"/><br/>
			Message: <textarea name="message"></textarea><br/>
			<input type="submit" value="Submit"/>
		</form>
		<p>
			<a href="./api/files">list</a> all files items
		</p>
	</body>
</html>