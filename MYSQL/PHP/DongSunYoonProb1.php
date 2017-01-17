
<html>
<head>
<title> Problem 1 </title>
</head>
<body>
<HR>
<H3>
   Bookstore Catalog
</H3>
<P>
<HR> 
<UL>
   	  

<?php

	$conn = mysqli_connect("cs377spring16.mathcs.emory.edu", "cs377", "Dj@B5WFd3Pf+", "bookstore");

   if (mysqli_connect_errno())    
   {      
      printf("Connect failed: %s\n", mysqli_connect_error());
      exit(1);
   }  

	if ( ! mysqli_select_db ($conn, "bookstore") )
	{
 		printf("Error: %s\n", mysqli_error($conn) );
 		exit(1);
 	}

 	$query = $_POST['query'];


 	$query = 'select book.ISBN, book.title as Title, book.year, book.publiName as Publisher, group_concat(author.name) as Author, book.price from book, publisher, author_book, author where book.publiName = publisher.name and book.isbn = author_book.isbn and author_book.aid = author.aid group by book.isbn';  




 	if ( ! ( $result = mysqli_query($conn, $query)) )
 	{
 		printf("Error: %s\n", mysqli_error($conn));
 		exit(1);
 	}
 

  	print("<UL>\n");
   	print("<TABLE bgcolor=\"#D4D9FF\" BORDER=\"3\">\n");
   	  
   	$printed = false;
   
   	while ( $row = mysqli_fetch_assoc( $result ) )
   	{      
      	if ( ! $printed )
      	{   
   	 		$printed = true;                 
   	  
   	 		print("<TR bgcolor=\"#91FF65\">\n");
   	 		foreach ($row as $key => $value)
   	 			{
   	    			print ("<TH>" . $key . "</TH>");             
   	 			}
   			print ("</TH>\n");
     	}   
   	  
   	  
      	print("<TR>\n");
      	foreach ($row as $key => $value)
      		{
   	 			print ("<TD>" . $value . "</TD>");
      		}
      	print ("</TR>\n");
   }      
   print("</TABLE>\n");
   print("</UL>\n");
   print("<P>\n");
   	  
   mysqli_free_result($result);
   	  
   mysqli_close($conn);

   ?>     

</UL>