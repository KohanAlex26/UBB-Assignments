package repository.xml;

import domain.entities.Trainer;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import repository.InMemoryRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainerXmlRepository extends InMemoryRepository<Long, Trainer> {
    List<Trainer> trainers;

    public TrainerXmlRepository(Validator<Trainer> validator){
        super(validator);
        loadTrainersFromXml();
    }

    /**
     * Reads the trainers from the trainers xml into memory.
     */
    private List<Trainer> loadTrainersFromXml(){
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = db.parse("data/trainers.xml");
            Element st = root.getDocumentElement();
            NodeList stList = st.getChildNodes();
            List<Trainer> trainers = new ArrayList<>();
            for(int i = 0; i < stList.getLength(); ++i){
                if(!(stList.item((i)) instanceof Element)){
                    continue;
                }
                Trainer trainer = createTrainerFromNode((Element) stList.item(i));
                try{
                    super.save(trainer);
                }catch(ValidatorException ex){
                    ex.printStackTrace();
                }
                trainers.add(trainer);
            }
            return trainers;
        }catch(ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a trainer from xml node
     * @param trainerElement - given DOM Element containing all the trainer attributes.
     * @return a Trainer instance with the attributes from the xml.
     */
    private static Trainer createTrainerFromNode(Element trainerElement){
        Trainer trainer = new Trainer();
        trainer.setId(Long.parseLong(trainerElement.getElementsByTagName("id").item(0).getTextContent()));
        trainer.setFirstName(trainerElement.getElementsByTagName("firstName").item(0).getTextContent());
        trainer.setLastName(trainerElement.getElementsByTagName("lastName").item(0).getTextContent());
        trainer.setAge(Integer.parseInt(trainerElement.getElementsByTagName("age").item(0).getTextContent()));
        return trainer;
    }

    @Override
    public Optional<Trainer> save(Trainer entity) throws ValidatorException{
        Optional<Trainer> optional = super.save(entity);
        if(optional.isPresent()){
            return optional;
        }
        try{
            saveToXml(entity);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Save a trainer into xml.
     * @param trainer - given Trainer
     */
    public static void saveToXml(Trainer trainer) throws ParserConfigurationException, IOException, SAXException, TransformerException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/trainers.xml");

        addTrainerToDom(trainer, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/trainers.xml"))
        );
    }

    private static void addTrainerToDom(Trainer trainer, Document document){
        Element root = document.getDocumentElement();
        Node trainerNode = createNodeFromTrainer(trainer, document);

        root.appendChild(trainerNode);
    }

    /**
     * Creates a xml node from a Trainer entity
     * @param trainer - given Trainer
     * @param document - given XML file
     * @return Node to be appended in a XML file.
     */
    private static Node createNodeFromTrainer(Trainer trainer, Document document){
        Element trainerElement = document.createElement("trainer");
        addChildWithTextContent(document, trainerElement, "id", Long.toString(trainer.getId()));
        addChildWithTextContent(document, trainerElement, "firstName", trainer.getFirstName());
        addChildWithTextContent(document, trainerElement, "lastName", trainer.getLastName());
        addChildWithTextContent(document, trainerElement, "age", Integer.toString(trainer.getAge()));

        return trainerElement;
    }

    private static void addChildWithTextContent(Document document, Element parent, String tagName, String textContent){
        Element childElement = document.createElement(tagName);
        childElement.setTextContent(textContent);
        parent.appendChild(childElement);
    }

    @Override
    public Optional<Trainer> delete(Long id){
        try{
            deleteFromXml(id);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex) {
            Optional<Trainer> optional = super.delete(id);
        }
        Optional<Trainer> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    /**
     * Deletes a trainer from xml based on id.
     * @param id must not be null.
     */
    private void deleteFromXml(Long id) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/trainers.xml");
        Element table = document.getDocumentElement();
        NodeList list = document.getElementsByTagName("trainer");
        for(int i = 0; i < list.getLength(); i++){
            Element trainerElement = (Element)list.item(i);
            Element idElement = (Element)trainerElement.getElementsByTagName("id").item(0);
            Long idValue = Long.parseLong(idElement.getTextContent());
            if(id.equals(idValue)){
                trainerElement.getParentNode().removeChild(trainerElement);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/trainers.xml"))
        );
    }
}
