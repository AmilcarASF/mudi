package br.com.alura.mvs.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptadorDeAcessos extends HandlerInterceptorAdapter {
	
	public static List<Acesso> acessos = new ArrayList<Acesso>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Acesso acesso = new Acesso();
		
		acesso.path = request.getRequestURI();
		acesso.date = LocalDateTime.now();
		
		request.setAttribute("acesso", acesso);
		
		return true;
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Acesso acesso = (Acesso)request.getAttribute("acesso");
		acesso.duracao = Duration.between(acesso.date, LocalDateTime.now());
		
		acessos.add(acesso);
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	public static class Acesso {
		private String path;
		private LocalDateTime date;
		private Duration duracao;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public Duration getDuracao() {
			return duracao;
		}
		public void setDuracao(Duration duracao) {
			this.duracao = duracao;
		}
		
	}

}
