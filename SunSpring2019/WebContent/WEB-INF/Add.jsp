<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Comment</title>
</head>
<body>
<h2>GuestBook - Add Comment</h2>
<form action="AddComment" method="post">
<table border="1" cellpadding="2" cellspacing="2" style="text-align:left">
  <tr>
    <th>Name</th>
    <td>
      <input type="text" name="name" size= '60' />
    </td>
  </tr>
  <tr>
    <th>Specialties</th>
    <td>
        <input type="text" name="message" size= '60'/>
<!--       <textarea name="message" rows="5" cols="60"></textarea>
 --> 
  </td>
  </tr>
    <tr>
    <th>Presentation</th>
    <td>
      <input type="text" name="specialties" size= '60'/>
    </td>
  </tr>
  <tr>
    <td colspan="2" rowspan="1">
      <input type="submit" name="add" value="Add" />
    </td>
<!--     <td></td> -->
  </tr>
</table>
</form>
</body>
</html>
