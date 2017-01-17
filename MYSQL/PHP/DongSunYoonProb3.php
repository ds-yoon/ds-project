<html>
   <head>
   <title>Problem 3</title>
   </head>
   <body>
   	  
   <H3>
   <HR>
   Checking Shopping Cart
   <HR>
   </H3>
   <P> 
   <UL>


   <form action="http://cs377spring16.mathcs.emory.edu/~dyoon2/DongSunYoonProb3.php"
   method="POST">
   <p>
    Email: <input name="email" rows="1" cols="30">
      </input>
    <p>
    Cart#: <input name = "cartid" rows="1" cols="15">

      </input>
      <p>
     
   <p> Shopping cart: <input type="submit" value="Check">

   </form>
   </UL>	  
   <HR>
   <UL>
   <?php
   	  
   $conn = mysqli_connect("cs377spring16.mathcs.emory.edu","cs377", "Dj@B5WFd3Pf+", "bookstore");
   	  
   if (mysqli_connect_errno())            # -----------  check connection error
   {      
      printf("Connect failed: %s\n", mysqli_connect_error());
      exit(1);
   }      
   	  
   if ( ! mysqli_select_db($conn, "bookstore") )          # Select DB
   {      
      printf("Error: %s\n", mysqli_error($conn));
      exit(1);
   }      
   	    
   $email = $_POST['email'];
   $carid = $_POST['cartid'];
   
   $content = array('email', 'cartid');

   
   foreach($content as $cont)
   {
    if(empty($_POST[$cont])){
      printf("Please enter every fields: %s\n");
      exit(1);
    }
   }

   $checkemail = "select name from customer where email = '$email'";
   $checkcartid = "select cartSequence from shopping_cart where cartSequence = '".$_POST['cartid']."' and cemail = '$email'";

  if (!($result11 = mysqli_query($conn, $checkemail)))
  {
    printf("Error: %s\n", mysqli_error($conn));
      exit(1);
  }

  if(mysqli_num_rows($result11) == 0){
     print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
     print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
     print("Please enter valid email\n");
     print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
     print("<P><HR><P>\n");
     
      exit(1);
  }

  if (!($result12 = mysqli_query($conn, $checkcartid)))
  {
    printf("Error: %s\n", mysqli_error($conn));
      exit(1);
  }

  if(mysqli_num_rows($result12) == 0){
     print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
     print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
     print("Please enter valid cart number\n");
     print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
     print("<P><HR><P>\n");
     
      exit(1);
  }

   $query = $_POST['query'];
   $query = "select distinct book.ISBN, book.title as Book, book.price from shopping_cart join item_cart on shopping_cart.cartSequence = item_cart.cartSequence join book on item_cart.isbn = book.isbn where item_cart.cemail = '$email' and item_cart.cartSequence = '".$_POST['cartid']."'";

   $query1 = $_POST['query1'];
   $query1 = " select distinct count(book.title) as TotalItems, sum(book.price) as TotalPrice from shopping_cart join item_cart on shopping_cart.cartSequence = item_cart.cartSequence join book on item_cart.isbn = book.isbn where item_cart.cemail = '$email' and item_cart.cartSequence = '".$_POST['cartid']."' group by shopping_cart.cemail";

   if ( ! ( $result = mysqli_query($conn, $query)) )      # Execute query
   {      
      printf("Error: %s\n", mysqli_error($conn));
      exit(1);
   }      
    if ( ! ( $result1 = mysqli_query($conn, $query1)) )      # Execute query
   {      
      printf("Error: %s\n", mysqli_error($conn));
      exit(1);
   }  

   if(mysqli_num_rows($result) == 0){
      print("<UL><TABLE bgcolor=\"#5DFFEA\" BORDER=\"2\">\n");
      print("<TR> <TD><FONT color=\"#030075\"><B><PRE>\n");
      print( "There are no items for : $email  with Cart# : ".$_POST['cartid']."" );  
      print("</PRE></B></FONT></TD></TR></TABLE></UL>\n");
      print("<P><HR><P>\n");
      exit(1);
   }	  
   print("<UL>\n");
   print("<TABLE bgcolor=\"#D4D9FF\" BORDER=\"3\">\n");
   	  
   $printed1 = false;
   while ( $row1 = mysqli_fetch_assoc( $result1 ) )
   {      
      if ( ! $printed1 )
      {   
       $printed1 = true;                 
        
       print("<TR bgcolor=\"#91FF65\">\n");
       foreach ($row1 as $key => $value)
       {
          print ("<TH>" . $key . "</TH>");             
       }
       print ("</TH>\n");
      }   
        
        
      print("<TR>\n");
      foreach ($row1 as $key => $value)
      {   
       print ("<TD>" . $value . "</TD>");
      }   
      print ("</TR>\n");
   }      
   print("</TABLE>\n");
   print("</UL>\n");
   print("<P>\n");
        
   mysqli_free_result($result1);


   print("<UL>\n");
   print("<TABLE bgcolor=\"#D9DDFF\" BORDER=\"3\">\n");

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
   <P> 
   <HR>