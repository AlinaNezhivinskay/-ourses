
public class Test {

	public static void main(String[] args) {
		AssemblyLine assemblyLine=new AssemblyLine(new CaseBuilder(),new SpringBuilder(),new KernelBuilder());
        IProduct pen=assemblyLine.assembleProduct(new Pen());
	}

}
