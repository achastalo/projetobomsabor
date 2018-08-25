<!DOCTYPE html>
<html lang="zxx">

<head>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
   
    <!-- Index-Page-CSS -->
   <link rel="stylesheet" href="resources/css/login/style.css" type="text/css"  media="all"> 
   
  	<link rel="stylesheet" type="text/css" href="resources/css/login/style2.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/product_responsive.css">
    
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
</head>

<body>
     <section class="login-wrap"> 
        <div class="main_w3agile">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
            <label for="tab-1" class="tab">Entre</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up">
            <label for="tab-2" class="tab">Cadastre-se</label>
            <div class="login-form">
            
                <!-- login form -->
                <div class="signin_wthree">
                    <form action="efetuaLogin" method="post" >
                       <div class="group">
                            <label for="login" class="label">E-mail*</label>
                           <input type="text" id="login" name="login" class="_input" required="required">
                        </div>
                        <div class="group">
                         	<label for="senha" class="label">Senha*</label>
							<input type="password" id="senha"  name="senha" class="_input" required="required">
                        </div>
                        <div class="group">
                            <input id="check" type="checkbox" class="check" checked>
                            <label for="check">
                                <span class="icon"></span>Mantenha-me conectado</label>
                        </div>
                        
                        <input type="submit" class="button" value="Entrar"/>
                         <div class="group">
                        	
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <h4><a href="#">Esqueceu a senha ?</a></h4>
                        </div>
                    </form>
                </div>
                <!-- login form -->
                
                <!-- cadastro form -->
                <div class="signup_wthree">
                    <form method="post" action="#">
                        <div class="group">
                        	<label for="user_name" class="label">Nome Completo*</label>
							<input type="text" id="user_name" class="_input" required="required">
                        </div>
                        <div class="group">
                         	<label for="senha_" class="label">Senha*</label>
							<input type="text" id="senha_" class="_input" required="required">
                        </div>
                        <div class="group">
                           	<label for="senha_confirma" class="label">Confirme sua senha*</label>
							<input type="text" id="senha_confirma" class="_input" required="required">
                        </div>
                        <div class="group">
                            <label for="email" class="label">E-mail*</label>
                            <input type="text" id="email" class="_input" required="required">
                        </div>
                         <div class="group">
                            <label for="email_confirma" class="label">Confirme seu E-mail*</label>
                            <input type="text" id="email_confirma" class="_input" required="required">
                        </div>
                       <div class="button cart_button"><a href="#">Concluir cadastro</a></div>
                        <div class="hr"></div>
                    </form>
                </div>
                <!-- //cadastro form -->
            </div>
        </div>
    </section> 
    <!-- //section -->
    
    <!-- script for password match -->
    <script>
        window.onload = function () {
            document.getElementById("senha_").onchange = validatePassword;
            document.getElementById("senha_confirma").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("senha_confirma").value;
            var pass1 = document.getElementById("senha_").value;
            if (pass1 != pass2)
                document.getElementById("senha_confirma").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("senha_confirma").setCustomValidity('');
            //empty string means no validation error
        }
    </script>
    <!-- script for password match -->
</body>
<!-- //Body -->

<script src="resources/js/jquery-3.2.1.min.js"></script>

</html>