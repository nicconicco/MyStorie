package com.cgalves.mystorie.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.cgalves.mystorie.processor.CustomAnnotationProcessorNico")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CustomAnnotationProcessorNico extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package com.cgalves.mystorie.generated;nn")
                .append("public class GeneratedClass {\n\n") // open class
                .append("tpublic String getMessage() {\n") // open method
                .append("\t\treturn ");


                        // for each javax.lang.model.element.Element annotated with the CustomAnnotationNico
        for (Element element : roundEnv.getElementsAnnotatedWith(CustomAnnotationNico.class)) {
            String objectType = element.getSimpleName().toString();
            // this is appending to the return statement
            builder.append(objectType).append(" says hello!\n");
        }


        builder.append(";") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class


//        String test = "package com.stablekernel.annotationprocessor.generated;\n\n" +
//                      "public class GeneratedClass {\n" +
//                      "public String getMessage(){\n"+
//                      "return"


        try { // write the file
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.cgalves.mystorie.generated.GeneratedClass");


            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }


        return true;
    }
}
