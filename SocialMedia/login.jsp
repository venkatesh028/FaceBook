<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Rubik:400,700'><link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="login-form">
  <form action = "login" method = "post">
    <h1>Login</h1>
    <div class="content">
      <div class="input-field">
        <input type="email" placeholder="Email" name = "email">
      </div>
      <div class="input-field">
        <input type="password" placeholder="Password" name = "password">
      </div>
      <a href="register.jsp" class="link">Create New Account?</a>
      <h3>${Message}</h3>
    </div>
    <div class="action">
      <button>Sign in</button>
    </div>
  </form>
</div>

</body>
</html>
