# 🛒 Guia de Implementação: Sistema de Carrinho de Compras

## ✅ O que foi implementado

### 1. **Modelo de Dados (CarrinhoItem.kt)**
- Nova classe `CarrinhoItem` que encapsula um `Produto` com sua `quantidade`
- Método `calcularSubtotal()` para calcular o valor total do item

### 2. **Contrato de Estado (CarrinhoContract.kt)**
Define a estrutura MVVM+C (Model-View-ViewModel-Contract):

- **State**: Lista de itens no carrinho + taxa de serviço
  - `calcularSubtotal()`: Soma de todos os itens
  - `calcularTotal()`: Subtotal + taxa

- **Events**: Ações que o usuário pode realizar
  - `AddToCart`: Adiciona um item ao carrinho
  - `RemoveFromCart`: Remove um item (por ID)
  - `UpdateQuantidade`: Atualiza a quantidade de um item

- **Effects**: Efeitos colaterais (Snackbars, navegação)
  - `ShowSnackbar`: Exibe mensagem de feedback
  - `NavigateToCheckout`: Navega para a tela de checkout

### 3. **ViewModel Compartilhado (CarrinhoViewModel.kt)**
Gerencia o estado do carrinho em toda a aplicação:

```kotlin
// Exemplo de uso
viewModel.handleEvent(CarrinhoContract.Event.AddToCart(item))
```

**Funcionalidades:**
- ✅ Adiciona novo item ao carrinho
- ✅ Incrementa quantidade se o produto já existe
- ✅ Remove itens do carrinho
- ✅ Atualiza quantidade de itens
- ✅ Valida quantidade mínima (não pode ser menor que 1)
- ✅ Exibe Snackbars com feedback ao usuário

### 4. **Tela de Detalhes Atualizada (ProdutoDetalhesScreen.kt)**

**Novo fluxo de "Adicionar ao Carrinho":**

```kotlin
Button(
    onClick = {
        val carrinhoItem = CarrinhoItem(
            produto = produto,
            quantidade = state.value.qtdProduto
        )
        viewModel.adicionarAoCarrinho(carrinhoItem, carrinhoViewModel)
        viewModel.handleEvent(Event.OnAddToCarrinho)
    }
)
```

**Features:**
- ✅ Cria um `CarrinhoItem` com a quantidade selecionada
- ✅ Passa o item para o `CarrinhoViewModel`
- ✅ Exibe confirmação em Snackbar
- ✅ Navega para a tela de carrinho após adicionar

### 5. **Tela de Carrinho Dinâmica (CarrinhoScreen.kt)**

**Antes:** Hardcoded com 2 `CarrinhoCard()`
**Depois:** Lista dinâmica que reage ao estado

```kotlin
if (state.value.itens.isEmpty()) {
    // Mostra mensagem "carrinho vazio"
} else {
    items(state.value.itens) { item ->
        CarrinhoCard(
            // ... dados do item
            onRemoveClick = { viewModel.handleEvent(Remove) },
            onAddClick = { viewModel.handleEvent(UpdateQuantidade) }
        )
    }
}
```

**Features:**
- ✅ Lista dinâmica de produtos no carrinho
- ✅ Botões de +/- quantidade funcionais
- ✅ Cálculo automático de subtotal e total
- ✅ Mensagem quando carrinho está vazio
- ✅ Botão "Confirmar Compra" aparece apenas com itens

### 6. **Card de Carrinho Melhorado (CarrinhoCard.kt)**
- ✅ Adicionados callbacks: `onRemoveClick` e `onAddClick`
- ✅ Removidos `TODO()`s antigos
- ✅ Botões agora executam ações reais

## 🔄 Fluxo Completo

```
ProdutoDetalhesScreen
    ↓ (usuário clica "Adicionar ao Carrinho")
    ↓ (cria CarrinhoItem com quantidade)
    ↓ (envia AddToCart event para CarrinhoViewModel)
    ↓ (CarrinhoViewModel adiciona/incrementa item)
    ↓ (emit efeito com confirmação Snackbar)
    ↓ (navega para CarrinhoScreen)
    ↓
CarrinhoScreen
    ↓ (observa estado do CarrinhoViewModel)
    ��� (renderiza lista de items dinamicamente)
    ↓ (usuário pode +/- quantidade ou remover)
    ↓ (cada ação emite evento para CarrinhoViewModel)
    ↓ (estado atualiza em tempo real)
```

## 💡 Como Usar

### Adicionar produto ao carrinho (do ProdutoDetalhesScreen):
```kotlin
val carrinhoItem = CarrinhoItem(
    produto = meuProduto,
    quantidade = 2
)
carrinhoViewModel.handleEvent(
    CarrinhoContract.Event.AddToCart(carrinhoItem)
)
```

### Remover produto do carrinho:
```kotlin
carrinhoViewModel.handleEvent(
    CarrinhoContract.Event.RemoveFromCart(produtoId = 5)
)
```

### Atualizar quantidade:
```kotlin
carrinhoViewModel.handleEvent(
    CarrinhoContract.Event.UpdateQuantidade(
        produtoId = 5,
        novaQuantidade = 3
    )
)
```

## 📁 Arquivos Criados/Modificados

| Arquivo | Tipo | Status |
|---------|------|--------|
| `model/CarrinhoItem.kt` | ✨ Novo | Criado |
| `presentation/carrinho/CarrinhoContract.kt` | ✨ Novo | Criado |
| `presentation/carrinho/CarrinhoViewModel.kt` | ✨ Novo | Criado |
| `presentation/detalhes/ProdutoDetalhesContract.kt` | 🔧 Modificado | Adicionado evento/effect |
| `presentation/detalhes/ProdutoDetalhesViewModel.kt` | 🔧 Modificado | Adicionado método `adicionarAoCarrinho` |
| `presentation/detalhes/ProdutoDetalhesScreen.kt` | 🔧 Modificado | Implementado click listener com lógica |
| `presentation/carrinho/CarrinhoScreen.kt` | 🔧 Modificado | Tornada dinâmica com ViewModel |
| `presentation/components/CarrinhoCard.kt` | 🔧 Modificado | Adicionados callbacks |

## 🎯 Próximas Etapas (Opcional)

1. **Persistência de dados**: Integrar com banco de dados (Room) para salvar carrinho
2. **Checkout**: Implementar tela de confirmação de compra
3. **Histórico de pedidos**: Salvar compras realizadas
4. **Favoritos**: Adicionar funcionalidade de wishlist
5. **Sincronização**: Backend API para carrinho na nuvem

