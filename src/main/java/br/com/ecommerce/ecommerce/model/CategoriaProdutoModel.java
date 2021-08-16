package br.com.ecommerce.ecommerce.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_CATEGORIA_PRODUTO")
public class CategoriaProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_CATEGORIA_PRODUTO_", sequenceName = "SEQ_CATEGORIA_PRODUTO", schema = "public", initialValue = 1, allocationSize = 1)
    @Column(name = "CODIGO", updatable = false, nullable = false)
    private Long codigo;

    @Column(name = "DESCRICAO", unique = true, nullable = false)
    private String descricao;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "IMAGEM", nullable = true)
    private byte[] imagem;

    @Column(name = "IS_ATIVO", columnDefinition = "boolean default true", nullable = false)
    private Boolean isAtivo;

    public CategoriaProdutoModel() { }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
