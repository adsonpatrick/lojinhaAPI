package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class produtoDataFactory {
    public static ProdutoPojo cirarProdutoComumComOValorIgualA(double valor){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("PlayStation6");
        produto.setProdutoValor(valor);
        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");
        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");
        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Controle");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        ComponentePojo segundoComponete = new ComponentePojo();
        segundoComponete.setComponenteNome("Memory Card");
        segundoComponete.setComponenteQuantidade(2);
        componentes.add(segundoComponete);

        produto.setComponentes(componentes);

        return produto;
    }
}
