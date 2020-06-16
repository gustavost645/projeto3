
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Plot extends JFrame {

    /**
     * Gustavo Steinhofel e Fernando J. Schmatz
     */
    private static final long serialVersionUID = 1L;
        
    public class TorreTelefone
    {
        //Usado para ordenação final
        public int idTorre;
        public String nameCel;
        public String toString() { return this.idTorre + ": " + this.nameCel; }

    }
    
    public static class SortByTorre implements Comparator<TorreTelefone>{
        public int compare(TorreTelefone p1,TorreTelefone p2)
        {
            return p1.idTorre - p2.idTorre;
        }
    }  

    

    
    public Plot() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int cordX = 650;
        int cordY = 650;
        PlotComponent pcomp = new PlotComponent(cordX, cordY);
        add(pcomp);

        TreeMap<String, Integer> mapFoneIdTorre = new TreeMap<String, Integer>();

        double torresX[] = { 0.1, 0.3, 0.7, 0.9 };
        double torresY[] = { 0.1, 0.3, 0.7, 0.9 };

        ArrayList<Torre> torres = new ArrayList<>();
        int c = 1;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                pcomp.addPoint(Color.RED, torresX[j] * cordX, torresY[i] * cordY);
                Torre torre = new Torre(c, torresX[j], torresX[i]);
                torres.add(torre);
                c++;
            }
        }

        // ADD FONES
        ArrayList<Telefone> telefones = new ArrayList<>();
        SimpleReader file = new SimpleReader("mobile.txt");
        String line = file.readLine();
        String array[] = new String[9999];

        for (int i = 0; i < array.length; i++) {
            array[i] = line;
            line = file.readLine();
        }
        file.close();

        // CRIA OS TELEFONES
        for (int i = 0; i < array.length; i++) {
            Telefone t = new Telefone(Telefone.retornaNome(array[i]), Telefone.retornaPosicaoX(array[i]),
                    Telefone.retornaPosicaoY(array[i]));
            telefones.add(t);
        }

        // ADICIONA AO PLOT
        for (int i = 0; i < array.length; i++) {
            pcomp.addPoint(Color.BLUE, (telefones.get(i).x * cordX), (telefones.get(i).y * cordY));
        }

        // calculo Manhattan
        for (Telefone tele : telefones) {
            double comp = 0;
            for (Torre torre : torres) {
                double resp = CalcularListarTorreFone.calcularDistManhattan(tele.x, torre.x, tele.y, torre.y);

                if (comp == 0) {
                    comp = resp;
                    mapFoneIdTorre.put(tele.nome, torre.id);
                } else if (resp < comp) {
                    comp = resp;
                    mapFoneIdTorre.replace(tele.nome, torre.id);
                }
                
            }
        }

        
        //Ordenação final por torre
        ArrayList<TorreTelefone> arrayT = new ArrayList<TorreTelefone>();
        Set<Map.Entry<String, Integer>> set = mapFoneIdTorre.entrySet();

        set.forEach((result) -> {
            TorreTelefone tf = new TorreTelefone();
            tf.idTorre = result.getValue();
            tf.nameCel = result.getKey();
            arrayT.add(tf); 
        });    
        Collections.sort(arrayT, new SortByTorre());

        //imprime torre telefone 
        for (TorreTelefone p : arrayT) {
            System.out.println("Torre -> "+p.idTorre+" | Fone -> "+p.nameCel);            
        }

        // TELA
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Plot());
    }
}

class PlotComponent extends JComponent {

    private ArrayList<Points> points = new ArrayList<>();

    public PlotComponent(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void addPoint(Color cor, double x, double y) {
        Points p = new Points();
        p.setNewCor(cor);
        p.setNewPoint(new Point2D.Double(x, y));
        points.add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        points.forEach((p) -> {

            Shape point;

            if (p.getNewCor().equals(Color.RED)) {
                point = new Rectangle2D.Double(p.getNewPoint().getX(), p.getNewPoint().getY(), 20, 20);
                g2d.setColor(p.getNewCor());
            } else {
                point = new Ellipse2D.Double(p.getNewPoint().getX(), p.getNewPoint().getY(), 6, 6);
                g2d.setColor(p.getNewCor());
            }

            g2d.draw(point);
        });

    }
}
