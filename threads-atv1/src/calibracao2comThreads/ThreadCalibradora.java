package calibracao2comThreads;

public class ThreadCalibradora extends Thread {

    private int linhaInicial;
    private int linhaFinal;
    private int somaDaThread;
    private String[] linhas;

    public ThreadCalibradora(String[] linhas, int linhaInicial, int linhaFinal) {
        this.linhas = linhas;
        this.linhaInicial = linhaInicial;
        this.linhaFinal = linhaFinal;
    }

    public int getSomaDaThread() {
        return somaDaThread;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public int getLinhaFinal() {
        return linhaFinal;
    }

    public void setLinhaInicial(int linhaInicial) {
        this.linhaInicial = linhaInicial;
    }

    public void setLinhaFinal(int linhaFinal) {
        this.linhaFinal = linhaFinal;
    }

    public void setSomaDaThread(int somaDaThread) {
        this.somaDaThread = somaDaThread;
    }

    @Override
    public void run(){
        int sum = 0;

        for (int i = (linhaInicial-1); i < linhaFinal; i++) {
            String linha = linhas[i];
            sum += Calibracao.valorCalibracao(linha);
        }

        setSomaDaThread(sum);

    }

}
