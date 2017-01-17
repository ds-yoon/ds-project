  <html>
   <head>
   <title> Problem 2</title>
   </head>
   <body>
      
   <H3>
   <HR>
   Add to Shopping Cart
   <HR>
   </H3>
   <P> 
   <UL>



   <form action="http://cs377spring16.mathcs.emory.edu/~dyoon2/DongSunYoonProb2.php"
   method="POST">
   <p>

  Email: <input name="email" rows="1" cols="30">
      </input>
    <p>
  Cart#: <input name = "cartid" rows="1" cols="15">

      </input>
      <p>
  ISBN : <input name = "isbn" rows="1" cols="15">

      </input>
      <p>

   <p> <input type="submit" value="Add to Cart">
   <p> Clear input: <input type="reset">

   </form>
    
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


   $email = $_POST['email'];
   $carid = $_POST['cartid'];
   $isbn = $_POST['isbn'];

   $content = array('email', 'cartid', 'isbn');

   
   foreach($content as $cont)
   {
    if(empty($_POST[$cont])){
      print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
      print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
      print("Please enter $cont\n");
      print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
      print("<P><HR><P>\n");
      
      exit(1);
    }
   }

   $query = $_POST['query'];
   $query1 = $_POST['query1'];
   $query2 = $_POST['query2'];

   $query = "select name from customer where email = '$email'";
   $query1 = "select title from book where ISBN = '".$_POST['isbn']."'";
   $query2 = "select cartSequence from shopping_cart where cartSequence = '".$_POST['cartid']."' and cemail = '$email'";

  if (!($result = mysqli_query($conn, $query)))
  {
    printf("Error: %s\n", mysqli_error($conn));
      exit(1);
  }

  if(mysqli_num_rows($result) == 0){
     print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
     print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
     print("Email is incorrect \n");
     print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
     print("<P><HR><P>\n");
    
      exit(1);
  }
   
  if (!($result1 = mysqli_query($conn, $query1)))
  {
    printf("Error: %s\n", mysqli_error($conn));
      exit(1);
  }
  if(mysqli_num_rows($result1) == 0){
     print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
     print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
     print("Bookstore does not carry that book \n");
     print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
     print("<P><HR><P>\n");
     
      exit(1);
  }

  if (!($result2 = mysqli_query($conn, $query2)))
  {
    printf("Error: %s\n", mysqli_error($conn));
      exit(1);
  }

  if(mysqli_num_rows($result2) == 0)
    {
     
      $sql1 = "INSERT INTO shopping_cart (cEmail, cartSequence) VALUES ('$email', '".$_POST['cartid']."')";
      mysqli_query($conn, $sql1);
      $sql = "INSERT INTO item_cart (cEmail, cartSequence, ISBN) VALUES ('$email', '".$_POST['cartid']."', '".$_POST['isbn']."')"; 
      mysqli_query($conn, $sql);
      print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
      print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
      print("Your item and cart number has been successfully added \n");
      print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
      print("<P><HR><P>\n");
      exit(1);
    }else{
      $sql = "INSERT INTO item_cart (cEmail, cartSequence, ISBN) VALUES ('$email', '".$_POST['cartid']."', '".$_POST['isbn']."')";
      mysqli_query($conn, $sql);
      print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
      print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
      print("Your item has been successfully added to your cart \n");
      print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
      print("<P><HR><P>\n");
      exit(1);
    }
    
  


   mysqli_free_result($result);
      
   mysqli_close($conn);
   ?>     
      
   </UL>
   <P> 