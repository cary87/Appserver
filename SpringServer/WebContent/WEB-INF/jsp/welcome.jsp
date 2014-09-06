
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CBOX</title>
<script type="text/javascript">
function checkInput() {
	if(!document.getElementsByName("email")[0].value) {
		alert("email is empty");
		return false;
		}
	if(!document.getElementsByName("password")[0].value) {
		alert("password is empty");
		return false;
		}
	if(!document.getElementsByName("name")[0].value) {
		alert("name is empty");
		return false;
		}
	if(!document.getElementsByName("age")[0].value) {
		alert("age is empty");
		return false;
		}
	return true;
}
</script>
<style>
.box{
 width:500px;
 margin:0 auto;
}
</style>
</head>
<body>
<h1 class="box" >Register For CBOX</h1>
<div class="box" >
<form action="./signup" method="post" onsubmit="return checkInput()">
  <p>Email: <input type="text" name="email" /></p>
  <p>Password: <input type="password" name="password" /></p>
  <p>Name: <input type="text" name="name" /></p>
  <p>Age: <input type="text" name="age"  onkeyup="this.value=this.value.replace(/\D/g,'')" style= "ime-mode:disabled " /></p>
  <p><input type="radio" name="sex" value="male" checked="checked"/> Male <input type="radio" name="sex" value="female" /> Female</p>
  <input type="submit" value="Signup"/>
</form>
</div>
</body>
</html>