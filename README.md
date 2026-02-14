# 🔢 Amazing Numbers

Um programa Java que descobre propriedades matemáticas fascinantes dos números. Porque matemática não precisa ser chata!

## O que ele faz?

O programa analisa números e identifica suas características especiais. Aqui estão as propriedades que ele detecta:

### 🔄 Números Palindrômicos
Números que são lidos da mesma forma de trás para frente.
- Exemplos: 121, 1331, 45654
- O número 12321 é palindrômico porque invertido continua 12321

### 🎯 Números Gapful
Números com 3+ dígitos divisíveis pelo número formado pelos dígitos das extremidades.
- Exemplo: 132 → primeiro dígito (1) + último dígito (2) = 12 → 132 ÷ 12 = 11 ✓
- Exemplo: 105 → 15 → 105 ÷ 15 = 7 ✓

### 🕵️ Números Spy
Números onde a soma dos dígitos é igual ao produto dos dígitos.
- Exemplo: 123 → soma (1+2+3=6) = produto (1×2×3=6) ✓
- Exemplo: 1124 → soma (1+1+2+4=8) = produto (1×1×2×4=8) ✓

### ⚡ Números Buzz
Números divisíveis por 7 OU que terminam em 7.
- Exemplos: 7, 14, 17, 21, 27, 35, 37...
- O 49 é buzz (divisível por 7), o 57 também (termina em 7)

### ☀️ Números Sunny
Números onde N+1 é um quadrado perfeito.
- Exemplo: 8 é sunny porque 8+1=9 (e 9=3²)
- Exemplo: 15 é sunny porque 15+1=16 (e 16=4²)

### 🔲 Números Quadrados Perfeitos
Números que são o quadrado de um inteiro.
- Exemplos: 1 (1²), 4 (2²), 9 (3²), 16 (4²), 25 (5²)...

### 🦘 Números Jumping
Números onde dígitos adjacentes diferem exatamente por 1.
- Exemplo: 121 → |1-2|=1, |2-1|=1 ✓
- Exemplo: 4545 → |4-5|=1, |5-4|=1, |4-5|=1 ✓
- Contra-exemplo: 123 → |1-2|=1 ✓, mas |2-3|=1 ✓... espera, esse também é!

### 🎲 Números Pares e Ímpares
A propriedade mais básica: divisível por 2 ou não.
- Pares: 2, 4, 6, 8, 10...
- Ímpares: 1, 3, 5, 7, 9...

### 🎪 Números Duck
Números que contêm o dígito 0 (mas não começam com 0).
- Exemplos: 10, 102, 1001, 5060
- Não são duck: 05 (começa com 0), 123 (sem zeros)

### Exemplos de uso
```
> Número: 123
   buzz: false
   duck: false
   palindromic: false
   gapful: true
   spy: true
   square: false
   sunny: false
   jumping: true
   even: false
   odd: true
```

## Por que esse projeto?

Ótimo para:
- 🧠 Praticar manipulação de números e strings em Java
- 💡 Treinar lógica de programação e algoritmos
- 🎓 Descobrir curiosidades matemáticas que você não sabia que existiam
- 🎮 Se divertir explorando padrões numéricos
- 📚 Aprender sobre propriedades matemáticas de forma prática

## Tecnologias

- Java
- Muito entusiasmo por números

---

Feito com ☕ e curiosidade matemática
