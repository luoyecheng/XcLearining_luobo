package demo.myJVMTest;

import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;

public class NameChecker {
    private static class NameCheckScanner extends ElementScanner8<Void,Void> {
        @Override
        public Void visitType(TypeElement e, Void unused) {
            scan(e.getTypeParameters(),unused);
            return super.visitType(e, unused);
        }
    }
}
