package apexlambda;

import apexlambda.model.ApexEnvironmentModel;
import awskms.AWSKMSEncrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GenerateEnvironmentsJsonBody extends JDialog {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String awsKeyID;

    private JPanel contentPane;

    private JButton buttonOpenFile;

    private JTextPane output;

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>STARTING PROGRAM<<<<<<<<<<<<<<");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initialise the the AWS arn-key-id: ");
        awsKeyID = scanner.next().trim();
        scanner.close();
        System.out.println(">>>>>>>>>>>>>PROGRAM STARTED<<<<<<<<<<<<<<");

        GenerateEnvironmentsJsonBody dialog = new GenerateEnvironmentsJsonBody();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public GenerateEnvironmentsJsonBody() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOpenFile);
        buttonOpenFile.addActionListener(e -> generateEnvironmentFile());
    }

    private void generateEnvironmentFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Apex Lambda Environment Generator");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            new File(String.valueOf(chooser.getSelectedFile()));
            try {
                Reader reader = new FileReader(chooser.getSelectedFile());
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);
                Map<String, String> environmentVariables = new LinkedHashMap<>();
                for (CSVRecord record : records) {
                    String key = record.get(0).trim();
                    boolean needsEncryption = Boolean.parseBoolean(record.get(2).trim());
                    String value = needsEncryption ? AWSKMSEncrypt.encrypt(awsKeyID, record.get(1).trim()) : record.get(1).trim();
                    environmentVariables.put(key, value);
                }
                String values = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ApexEnvironmentModel(environmentVariables));
                output.setText(values);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Selection ");
        }
    }

}
