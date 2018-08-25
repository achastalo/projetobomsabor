package br.com.bomsabor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	/***
	 * Sobrescrevendo o método preHandle o usuário só pode acessar os métodos do
	 * LoginController SEM ter feito o login. Caso outra lógica seja chamada é
	 * preciso verificar se o usuário existe na sessão. Existindo na sessão,
	 * seguiremos o fluxo normalmente, caso contrário indicaremos que o usuário não
	 * está logado e que deverá ser redirecionado para o formulário de login.
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
