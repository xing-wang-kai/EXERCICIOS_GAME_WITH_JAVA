# EXERCICIOS_GAME_WITH_JAVA

# Part 01 - Basic structure to create Games with java;

A estrutura básica para o Game precisa sempre está atualizando para que quando o programa 
finalize o jogo não seja interrompido, desta forma é importante utilizar de técnicas 
que constroem um sistema de atualização constante para renderizar os graficos e atualizar 
as informações dos games.
Como estratégia em Java usamos a interface Runnable que possuo o méthodo Run que opera 
constante na class principal sendo chamado constatemente durante a operação. para isso tanbém precisamos 
definir os métodos UPDATE e RENDER.
Dentro do Método UPDATE será colocado todos dados sobre o código do Game.
O Método RENDER será responsável pela interação com a UI gerada para o usuário.

Estrutura básica de código em JAVA para GAMES.

```java

public class Game extends Canvas implements Runnable{
	
	private boolean isRunning;
	private Thread thread;
	
		
	public Game() {
		
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.Start();
	}
	
	public synchronized void Start() 
	{
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public synchronized void Stop()
	{
		this.isRunning = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Update() 
	{
		//Este método é resonsável pelas implementações de lógica do Game.
		
	}
	
	public void Render() 
	{
		//Esse método será responsável pela renderização.
	}
	
	/**
		Este médoto é o coração do game, será configurado para rodar conforme os frame do game e manter o mesmo rodando até ser encerrado.
	**/
	public void run() 
	{
		while(isRunning)
		{
		}
	}
		
		this.Stop();
	}
}


```