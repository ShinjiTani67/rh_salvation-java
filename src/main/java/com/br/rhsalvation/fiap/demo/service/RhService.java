package com.br.rhsalvation.fiap.demo.service;

import com.br.rhsalvation.fiap.demo.dto.RhDTO;
import com.br.rhsalvation.fiap.demo.entity.Rh;
import com.br.rhsalvation.fiap.demo.repository.RhRepository;
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
        dto.setNome(rh.getNome());
        dto.setEmail(rh.getEmail());
        dto.setSenha(rh.getSenha());
        return dto;
    }

    private Rh convertToEntity(RhDTO dto) {
        Rh rh = new Rh();
        rh.setUuid(dto.getUuid());
        rh.setNome(dto.getNome());
        rh.setEmail(dto.getEmail());
        rh.setSenha(dto.getSenha());
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
        return repository.findByUuid(uuid)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("RH com id " + uuid + " não encontrado"));
    }

    public RhDTO findByEmail(String email) {
        return repository.findByEmail(email)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("RH com email " + email + " não encontrado"));
    }
}
