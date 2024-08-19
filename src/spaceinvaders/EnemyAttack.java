package spaceinvaders;

public class EnemyAttack extends Proyectil {
    public EnemyAttack(int x, int y){
        super(x, y);
    }

    @Override
    public void update(){
        y++;
    }
}