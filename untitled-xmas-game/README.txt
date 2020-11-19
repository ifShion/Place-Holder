			Untitled Xmas Game
				Notas
=======================================================================

Esse arquivo contém detalhes do vir-a-ser do projeto com todas as 
mecanicas necessárias a serem desenvolvidas e implementadas

=======================================================================
CONTEÚDO

* DESCRIÇÃO
* ROTEIRO
* MISSÕES

=======================================================================
DESCRIÇÃO

Há no total 5 missões a serem cumpridas sendo que cada uma será 
destinada a uma criança e a uma região diferente do mapa. Cada missão
terá seu próprio gênero e dará ao jogador um item necessário para 
cumprir uma sexta e ultima missão. Controles simplificados definidos 
por direcional, dois botões de ação e um de pausa

=======================================================================
ROTEIRO
Cada criança possui sua história que juntas formam todo o background do 
jogo. Caso jogador perca uma das crianças ele não conseguirá descobrirá
tudo o que ocorreu.

=======================================================================
CRIANÇAS

[Alpha] - 
[Bravo] -
[Charlie] - 
[Delta] - 
[Echo] - 

=======================================================================
MISSÕES

* PLATAFORMA:
	Metroidvania estilo Hollow Knight
	Item a ser resgatado: ???
	Controles: Direcional, Ataque, Especial Generico
	Inimigos: Pula
		  Explode após morrer
		  Avança depois que te vê
		  Lança projetil em parabola ou reta
	Boss: Estagios definido pela quantidade de vida
		Estágio 1: Ataques básicos corpo a copor e lançamentos 
		de curta distância
		Estágio 2: Maior movimentação
			   Padrão de movimento igual porem mais rápido


* MINI-GAME:
	Overcooked, uma sala pequena com alguns quebra-cabeças
	Item a ser resgatado: ???
	Sistema de inventario

	Sala de maquinas em crise, personagem tem que correr para consertar
	tudo evitar que a sala exploda e montar seu item
	
	Mini-games:
		- GUITAR HERO (Apertar a tecla e vc apertar o botão)
		- LABIRINTO (caixa com um item dentro, vc tem que girar o 
		item dentro do labirinto pra ele cair)
		- REAÇÃO (tipo um Hit the Squirl, mas com menos alvos e 
		mais rápido)
		- SAFE ZONE (sei lá o nome, mas é tipo a pescaria do 
		Stardew Valley)
		- ORGANIZAR COISA (Tem uma caixa com bolinha colorida e
		tem que separar por cor)

* TOP DOWN:
	Zelda, coleção de salas interligadas
	Sistema de batalha
	Item a ser resgatado: ???
	Controles: Usar Item, Interação, Direcional;
	Inimigos:
		 Espinhos - encosta e dá dano
		 Arqueiro
		 Espadachim
	       	 Mini Boss (Ensina a derrotar o boss)
	
	Boss: Boneco de neve maligno
		Sala simples e fechada.
		Ponto fraco baseado no item usado pelo personagem
		O boneco atiraria bolas de neve na direção do jogador

* RUN AND GUN:
	Contra, Plataforma linear//caminho único
	Item a ser resgatado: ???
	Controles: Ataque, Pulo, Direcional;
	Inimigos:
		Não atiram, mas avançam para o jogador
		Atiram no jogador
		Atiram para frente
		Não atiram, mas viajam rotas definidas

	Boss: Estagio definido pela quantidade de vida
		Grande
		Atira padrões únicos
		Summona minions

		Estagio 1:
			Ataques basicos
			Poucos minions
		Estagio 2:
			Ataques múltiplos
			Muitos minions
			Ataques padronizados
		Estagio 3:
			Ataques múltiplos rápidos
			Area denial
			Minions
* STEALTH 
	Luigi's Ghost Mansion, Série de salas retangulares com paredes
	Item a ser resgatado: ???
	Controles: botãoDeTiroStun, botãoDeCorrida, direcional;

	Meio labirinto
	Escuro
	Inimigos com lanteninha



* SEXTA MISSÃO: tower-defense
	Kingdom Rush, Uma rota com espaços para torre
		Controles: Botão de Seleção, botão de cancelar, 
			direcional;
		Inimigos:
			Mais rápido, menos vida
			Mais lento, mais vida
			Spawna [minions] quando cai
			Normal, pouca vida
			Normal, vida média
			Normal, muita vida
			[minions] rápidos, pouquíssima vida
			
		Torres: As torres dependem muito dos itens dos caras,
			então vou deixar em branco	
			/* Exemplo de torres
			* Arco e flecha (alvo único, rápido)
			* Morteiro de enfeite de árvore (Splash damage,
			lento)
			* Guarita de biscoitos na estrada
			* Canhão (alvo único, lento)
			* Forte de bolinha de neve ??? (lento, dá debuff
			no inimigo)
			*/
			Alpha(Item){
				level1()
				level2()
				level3()
			}
			Bravo(Item){
				level1()
				level2()
				level3()
			}
			Charlie(Item){
				level1()
				level2()
				level3()
			}
			Delta(Item){
				level1()
				level2()
				level3()
			}
			Echo(Item){
				level1()
				level2()
				level3()
			}
		}
		
		teste danzin eclipse
=======================================================================
