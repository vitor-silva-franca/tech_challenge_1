package com.vitorsilvafranca.tech_challenge_1.application.cardapio;

import com.vitorsilvafranca.tech_challenge_1.domain.model.cardapio.Item;
import com.vitorsilvafranca.tech_challenge_1.domain.repository.ItemRepository;
import com.vitorsilvafranca.tech_challenge_1.shared.ApplicationException;
import com.vitorsilvafranca.tech_challenge_1.shared.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarItemUseCaseTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private DeletarItemUseCase deletarItemUseCase;

    @Test
    void deveDeletarItemComSucesso() {
        Long id = 1L;
        Item item = new Item();

        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        deletarItemUseCase.deletar(id);

        verify(itemRepository).findById(id);
        verify(itemRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdForNull() {
        assertThrows(ApplicationException.class, () -> deletarItemUseCase.deletar(null));

        verifyNoInteractions(itemRepository);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoForEncontrado() {
        Long id = 1L;

        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> deletarItemUseCase.deletar(id));

        verify(itemRepository).findById(id);
        verify(itemRepository, never()).deleteById(id);
    }
}
