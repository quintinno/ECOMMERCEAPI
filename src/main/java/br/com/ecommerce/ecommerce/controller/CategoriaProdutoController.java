package br.com.ecommerce.ecommerce.controller;

import br.com.ecommerce.ecommerce.model.CategoriaProdutoModel;
import br.com.ecommerce.ecommerce.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/categoria-produto")
@CrossOrigin("*")
public class CategoriaProdutoController implements Serializable {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoModel>> recuperarCategoriaProdutoList() {
        return ResponseEntity.ok().body(this.categoriaProdutoService.recuperarCategoriaProdutoList());
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<CategoriaProdutoModel> cadastrarCategoriaProduto(@RequestPart("categoriaProduto") String categoriaProduto, @RequestPart("imagemCategoria") MultipartFile imagemCategoria) {
        try {
            System.out.println(categoriaProduto);
            System.out.println(imagemCategoria.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(this.categoriaProdutoService.cadastrarCategoriaProduto(this.categoriaProdutoService.converterJSONParaObject(categoriaProduto, imagemCategoria)));
    }

    @GetMapping("/{codigoCategoriaProduto}")
    public ResponseEntity<CategoriaProdutoModel> recuperarCategoriaProduto(@PathVariable Long codigoCategoriaProduto) {
        return ResponseEntity.ok().body(this.categoriaProdutoService.recuperarCategoriaProduto(codigoCategoriaProduto));
    }

    @GetMapping(value = "/recuperar-imagem/{codigoCategoriaProduto}")
    public ResponseEntity<ByteArrayResource> recuperarImagemCategoriaProduto(@PathVariable Long codigoCategoriaProduto) throws IOException {
        CategoriaProdutoModel categoriaProdutoModel = this.categoriaProdutoService.recuperarCategoriaProduto(codigoCategoriaProduto);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .contentType(MediaType.valueOf(MediaType.IMAGE_GIF_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" +  "categoria-produto.jpg\"")
                .body(new ByteArrayResource(categoriaProdutoModel.getImagem()));
    }

}
