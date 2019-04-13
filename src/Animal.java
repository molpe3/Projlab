import java.io.IOException;

public abstract class Animal implements Printable {
	protected Panda pulled;
	protected Tile tile;
	protected String name = "UNNAMED";

	public void Fall() {
		Destroy();
	}

	public void Destroy() {
		tile.RemoveAnimal();
		Game.getInstance().DeleteAnimal(this);
	}

	public abstract void Move(int side);

	public abstract boolean CollideWithPanda(Panda p);

	public abstract boolean CollideWithOrangutan(Orangutan o);

	public void Disband() {
		if (pulled != null) {
			pulled.Disband();
		}
	}

	public Tile GetTile() {
		return tile;
	}

	public Panda GetPulled() {
		return pulled;
	}

	public void SetPulled(Panda p) {
		pulled = p;
	}

	public void SetTile(Tile t) {
		tile = t;
	}

	public abstract int AskForRandomNumber() throws IOException;
}
