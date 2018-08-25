package br.com.bomsabor.controller;

import java.text.Normalizer.Form;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.bomsabor.dao.UsuarioDao;
import br.com.bomsabor.modelo.Usuario;

@Controller
public class LoginController {
	
	//se a url solicitada for loginForm o método devolve a pagina formulario-login
	@RequestMapping("loginForm")
	public String loginForm() {
		return "login/login";
	}
	
	//valida o login de formulario-login.jsp
	@RequestMapping(value = "efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if (new UsuarioDao().existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "template/index";//se existe usuario direciona para a tela
		}
		return "redirect:loginForm";//senão retorna pro login
	}
	
//	@RequestMapping(value = "efetuaLogin", method = RequestMethod.POST)
//	    public String recebeForm(@ModelAttribute("form") Usuario usuario, Model model,HttpSession session){
//	        model.addAttribute("msg", "Você enviou: " + usuario.getLogin() + " " + usuario.getSenha() );
//	        if (new UsuarioDao().existeUsuario(usuario)) {
//				session.setAttribute("usuarioLogado", usuario);
//				return "menu";//se existe usuario direciona para a tela
//			}
//			return "redirect:loginForm";//senão retorna pro login
//	    }
	 
	@RequestMapping("logout")
	public	String	logout(HttpSession	session) {	
		session.invalidate();
		return "redirect:loginForm";
	}

}
