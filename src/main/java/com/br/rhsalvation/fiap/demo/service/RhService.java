package com.br.rhsalvation.fiap.demo.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RhService {
    private final RhRepository repository;

    private RhDTO convertToDTO(Rh rh) {
        RhDTO dto = new RhDTO();
        dto.setUuid(rh.getUuid());
        dto.setCpf(rh.getCpf());
        dto.setNome(rh.getNome());
        dto.setEmail(rh.getEmail());
        dto.setTelefone(rh.getTelefone());
        return dto;
    }

    private Rh convertToEntity(RhDTO dto) {
        Rh rh = new Rh();
        rh.setUuid(dto.getUuid());
        rh.setCpf(dto.getCpf());
        rh.setEmail(dto.getEmail());
        rh.setNome(dto.getNome());
        rh.setTelefone(dto.getTelefone());
        return rh;
    }

    public RhDTO save(RhDTO rhDTO) {
        Rh rh = convertToEntity(rhDTO);

        if (rh.getUuid() == null) {
            rh.setUuid(UUID.randomUUID());
        }
        rh = repository.save(rh);
        return convertToDTO(rh);
    }

    public List<RhDTO> getRh() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

    public RhDTO findById(UUID uuid) {
        Optional<Rh> byUuid = repository.findByUuid(uuid);
        if (byUuid.isPresent())
            return convertToDTO(byUuid.get());

        throw new RuntimeException("RH com id " + uuid + " não encontrado");
    }

    public RhDTO findByEmail(String email) {
        Optional<Rh> rh = repository.findByEmail(email);
        if (rh.isPresent())
            return convertToDTO(rh.get());
        throw new RuntimeException("RH com email " + email + " não encontrado");
    }
}
