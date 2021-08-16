package br.com.ecommerce.ecommerce.service;

import br.com.ecommerce.ecommerce.model.CategoriaProdutoModel;
import br.com.ecommerce.ecommerce.repository.CategoriaProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Service
public class CategoriaProdutoService implements Serializable {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    public List<CategoriaProdutoModel> recuperarCategoriaProdutoList() {
        return this.categoriaProdutoRepository.findAll();
    }

    public CategoriaProdutoModel cadastrarCategoriaProduto(CategoriaProdutoModel categoriaProdutoModel) {
        return this.categoriaProdutoRepository.save(categoriaProdutoModel);
    }

    public CategoriaProdutoModel converterJSONParaObject(String categoriaProduto, MultipartFile imagemCategoria) {
        CategoriaProdutoModel categoriaProdutoModel = new CategoriaProdutoModel();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            categoriaProdutoModel = objectMapper.readValue(categoriaProduto, CategoriaProdutoModel.class);
            categoriaProdutoModel.setImagem(imagemCategoria.getBytes());
            categoriaProdutoModel.setAtivo(true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoriaProdutoModel;
    }

    public CategoriaProdutoModel recuperarCategoriaProduto(Long codigoCategoriaProduto) {
        return this.categoriaProdutoRepository.findById(codigoCategoriaProduto).get();
    }

    public byte[] recuperarImagemCategoriaProduto(Long codigoCategoriaProduto) {
        return this.categoriaProdutoRepository.findById(codigoCategoriaProduto).get().getImagem();
    }

}
