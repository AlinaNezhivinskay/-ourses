
public class Test {

	public static void main(String[] args) {
		AssemblyLine assemblyLine=new AssemblyLine(new CaseBuilder(),new SpringBuilder(),new KernelBuilder());
        Pen pen=new Pen();
        assemblyLine.assembleProduct(pen);
	}

}
