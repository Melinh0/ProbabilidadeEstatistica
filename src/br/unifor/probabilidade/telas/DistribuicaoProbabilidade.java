package br.unifor.probabilidade.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.unifor.probabilidade.util.Fatorial;

@SuppressWarnings("serial")
public class DistribuicaoProbabilidade extends JFrame {
	private JLabel jlNumeroTotalDeTentativas;
	private JLabel jlNumeroDeTentativasComSucesso;
	private JLabel jlProbabilidadeDeSucesso;
	private JLabel jlProbabilidadeDeFracasso;
	private JLabel jlCasoAoMenos;
	private JLabel jlCasoNoMaximo;
	private JLabel jlResultadoPx;
	
	private JTextField jtN;
	private JTextField jtK;
	private JTextField jtP;
	private JTextField jtQ;
	private JTextField jtPxMenorQuek;
	private JTextField jtPxMaiorQueK;
	private JTextField jtPx;

	private JPanel jpPanel;

	private JButton btResultado;
	private JButton btLimpar;

	public DistribuicaoProbabilidade() {
		super("Distribuição de Probabilidade");
		startComponets();

		jpPanel.setLayout(null);
		jlNumeroTotalDeTentativas.setBounds(10, 10, 210, 20);
		jlNumeroDeTentativasComSucesso.setBounds(10, 40, 210, 20);
		jlProbabilidadeDeSucesso.setBounds(10, 70, 210, 20);
		jlProbabilidadeDeFracasso.setBounds(10, 100, 210, 20);
		jlCasoAoMenos.setBounds(10, 130, 210, 20);
		jlCasoNoMaximo.setBounds(10, 160, 210, 20);
		jlResultadoPx.setBounds(10, 190, 210, 20);

		jtN.setBounds(230, 10, 70, 20);
		jtN.addKeyListener(new NoKeys());
		jtK.setBounds(230, 40, 70, 20);
		jtK.addKeyListener(new NoKeys());
		jtP.setBounds(230, 70, 70, 20);
		jtP.addKeyListener(new NoKeys());
		jtQ.setBounds(230, 100, 70, 20);
		jtQ.addKeyListener(new NoKeys());
		jtPxMaiorQueK.setBounds(230, 130, 70, 20);
		jtPxMaiorQueK.setEditable(false);
		jtPxMaiorQueK.setBackground(Color.WHITE);
		jtPxMaiorQueK
				.setToolTipText("Probabilidade para todos os casos menores ou iguais a 'k'");
		jtPxMenorQuek.setBounds(230, 160, 70, 20);
		jtPxMenorQuek.setEditable(false);
		jtPxMenorQuek.setBackground(Color.WHITE);
		jtPxMenorQuek
				.setToolTipText("Probabilidade para todos os casos maiores ou iguais a 'k'");
		jtPx.setBounds(230, 190, 70, 20);
		jtPx.setEditable(false);
		jtPx.setBackground(Color.WHITE);

		btResultado.setBounds(200, 220, 100, 20);
		btLimpar.setBounds(40, 220, 100, 20);
		btResultado.addActionListener(new TratamentoBotaoListener());
		btLimpar.addActionListener(new TratamentoBotaoListener());

		jpPanel.add(jlNumeroTotalDeTentativas);
		jpPanel.add(jlNumeroDeTentativasComSucesso);
		jpPanel.add(jlProbabilidadeDeSucesso);
		jpPanel.add(jlProbabilidadeDeFracasso);
		jpPanel.add(jlCasoAoMenos);
		jpPanel.add(jlCasoNoMaximo);
		jpPanel.add(jlResultadoPx);
		jpPanel.add(jtN);
		jpPanel.add(jtK);
		jpPanel.add(jtP);
		jpPanel.add(jtQ);
		jpPanel.add(jtPxMaiorQueK);
		jpPanel.add(jtPxMenorQuek);
		jpPanel.add(jtPx);
		jpPanel.add(btResultado);
		jpPanel.add(btLimpar);

		getContentPane().add(jpPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
	}

	public void startComponets() {
		jlNumeroTotalDeTentativas = new JLabel("Numero total de tentativas",
				JLabel.RIGHT);
		jlNumeroDeTentativasComSucesso = new JLabel(
				"N de tentativas com sucesso", JLabel.RIGHT);
		jlProbabilidadeDeSucesso = new JLabel("Probabilidade de sucesso",
				JLabel.RIGHT);
		jlProbabilidadeDeFracasso = new JLabel("Probabilidade de fracasso",
				JLabel.RIGHT);
		jlCasoAoMenos = new JLabel("No máximo - P(x<=k)", JLabel.RIGHT);
		jlCasoNoMaximo = new JLabel("Ao menos - P((x>=k)", JLabel.RIGHT);
		jlResultadoPx = new JLabel("P(X=K)", JLabel.RIGHT);
		jtN = new JTextField();
		jtK = new JTextField();
		jtP = new JTextField();
		jtQ = new JTextField();
		jtPxMaiorQueK = new JTextField();
		jtPxMenorQuek = new JTextField();
		jtPx = new JTextField();
		jpPanel = new JPanel();
		btResultado = new JButton("Resultado");
		btLimpar = new JButton("Limpar");
	}

	public static void main(String[] args) {
		new DistribuicaoProbabilidade();
	}

	class TratamentoBotaoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btResultado) {
				Double resultadoPX = 0.0;
				DecimalFormat df = new DecimalFormat("#.########");
				Fatorial util = new Fatorial();

				if (jtN.getText().equals("") || jtK.getText().equals("")
						|| jtP.getText().equals("") || jtQ.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Todos os campos devem ser preenchidos");
				} else if ((util.convertDouble(jtP.getText()) + util
						.convertDouble(jtQ.getText())) > 1) {
					JOptionPane.showMessageDialog(null,
							"Sucesso + Fracasso devem ser menor ou igual a 1");
				} else {

					resultadoPX = util.calculoPx(new Integer(jtN.getText()),
							new Integer(jtK.getText()),
							util.convertDouble(jtP.getText()),
							util.convertDouble(jtQ.getText()));
					
					jtPx.setText(df.format(resultadoPX) + "");

					resultadoPX = util.casoAoMenos(new Integer(jtN.getText()),
							new Integer(jtK.getText()),
							util.convertDouble(jtP.getText()),
							util.convertDouble(jtQ.getText()));
					jtPxMenorQuek.setText(df.format(resultadoPX));

					resultadoPX = util.casoNoMaximo(new Integer(jtN.getText()),
							new Integer(jtK.getText()),
							util.convertDouble(jtP.getText()),
							util.convertDouble(jtQ.getText()));
					jtPxMaiorQueK.setText(df.format(resultadoPX));

				}

			} else if (e.getSource() == btLimpar) {
				jtN.setText("");
				jtK.setText("");
				jtP.setText("");
				jtQ.setText("");
				jtPxMaiorQueK.setText("");
				jtPxMenorQuek.setText("");
				jtPx.setText("");
			}
		}
	}

	class NoKeys implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			String caracteres = "0987654321,";
			if (!caracteres.contains(e.getKeyChar() + "")) {
				e.consume();
			}
		}

	}
}
