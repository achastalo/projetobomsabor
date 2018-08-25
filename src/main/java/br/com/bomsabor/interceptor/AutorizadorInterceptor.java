package br.com.bomsabor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	/***
	 * Sobrescrevendo o m�todo preHandle o usu�rio s� pode acessar os m�todos do
	 * LoginController SEM ter feito o login. Caso outra l�gica seja chamada �
	 * preciso verificar se o usu�rio existe na sess�o. Existindo na sess�o,
	 * seguiremos o fluxo normalmente, caso contr�rio indicaremos que o usu�rio n�o
	 * est� logado e que dever� ser redirecionado para o formul�rio de login.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources")) {
			return true;
		}
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		response.sendRedirect("loginForm");
		return false;
	}

}
