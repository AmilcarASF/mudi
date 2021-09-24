package br.com.alura.mvs.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvs.mudi.model.Pedido;
import br.com.alura.mvs.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

	@NotBlank
	private String nomeProduto;

	@NotBlank
	private String urlProduto;

	@NotBlank
	private String urlImagem;
	private String descricao;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String utlProduto) {
		this.urlProduto = utlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setNomeProduto(nomeProduto);
		pedido.setDescricao(descricao);
		pedido.setUrlImagem(urlImagem);
		pedido.setUrlProduto(urlProduto);

		pedido.setStatus(StatusPedido.AGUARDANDO);

		return pedido;
	}

}
