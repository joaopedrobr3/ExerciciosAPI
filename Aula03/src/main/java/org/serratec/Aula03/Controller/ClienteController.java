package org.serratec.Aula03.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.serratec.Aula03.Domain.Cliente;
import org.serratec.Aula03.Repository.ClienteRepository;
import org.serratec.Aula03.enumerated.StatusCliente;
import org.serratec.Aula03.enumerated.TipoCliente;
import org.serratec.Aula03.exeption.EnumValidationException;
import org.serratec.Aula03.exeption.RecursoNaoEncontradoException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
     
	// Injeção de dependência do repositório de clientes
     @Autowired
     private ClienteRepository clienteRepository;
     
	//  Método para listar todos os clientes
     @GetMapping
     public List<Cliente> listar(){
          return clienteRepository.findAll();
     }
     
	//  Método para buscar cliente por ID, 
	// lançando exceção personalizada se não encontrado
     @GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		 Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
		return ResponseEntity.ok(cliente).getBody();
	}

    //  Método para inserir um novo cliente, 
	// validando os dados de entrada
     @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
    
	// Método para atualizar um cliente existente, 
	// verificando se o cliente existe antes de atualizar
     @PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente,
			@PathVariable Long id) {
		
		// Verificar se o cliente existe antes de tentar atualizar, 
		// retornando 404 Not Found se não existir
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		// Garantir que o ID do cliente a ser atualizado seja o 
		// mesmo do caminho da URL para evitar inconsistências
		cliente.setId(id);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
    
	// Método para remover um cliente, verificando se o cliente existe antes de tentar remover
     @DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		// Verificar se o cliente existe antes de tentar remover, 
		// retornando 404 Not Found se não existir
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		// Remover o cliente do banco de dados usando o ID fornecido 
		// e retornar uma resposta sem conteúdo (204 No Content)
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
    
	// Método para buscar cliente por nome, utilizando stream para filtrar a 
	// lista de clientes e retornando o primeiro resultado encontrado
	@GetMapping("/buscar")
	public ResponseEntity<Cliente> buscarPorNome(@RequestParam String nome) {
		// Utilizar o método findAll() do repositório para obter a lista de clientes, em seguida, usar stream para filtrar os clientes pelo nome fornecido e 
		// encontrar o primeiro cliente que 
		// corresponda ao nome, ignorando diferenças de maiúsculas e minúsculas
		Optional<Cliente> cliente = clienteRepository.findAll()
		        .stream()
				.filter(c -> c.getNome().equalsIgnoreCase(nome))
				.findFirst();
		
		// Verificar se um cliente com o nome fornecido foi encontrado, retornando 200 OK com o cliente encontrado ou 404 Not Found se nenhum cliente for encontrado
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
     
    @GetMapping("/status/{status}")
public ResponseEntity<List<Cliente>> buscarPorStatus(@PathVariable StatusCliente status) throws EnumValidationException {
    // Converter para maiúsculas e validar usando o método verifica() do enum
    StatusCliente statusEnum = StatusCliente.verifica(status.name().toUpperCase());
    List<Cliente> clientes = clienteRepository.findByStatus(statusEnum);
    
    if(clientes.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(clientes);

}
 @GetMapping("/tipo/{TipoCliente}")
public ResponseEntity<List<Cliente>> buscarPorTipoCliente(@PathVariable TipoCliente tipo) throws EnumValidationException {
    // Converter para maiúsculas e validar usando o método verifica() do enum
    TipoCliente tipoEnum = TipoCliente.verifica(tipo.name().toUpperCase());
    List<Cliente> clientes = clienteRepository.findByTipo(tipoEnum);
    
    if(clientes.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(clientes);
	
}
} 
