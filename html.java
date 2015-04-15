import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class html {
	public html(UseCase uc) {
		LoadFileBox lfb = new LoadFileBox(true);
		String directory = lfb.getFileSelected()
				+ "\\" + uc.getID() + ".html";;
				
		String html = "";
		String head = "<head><style>#header {background-color:black;color:white;"
				+ "text-align:center;padding:5px;}#table{width:100%;height:100%;"
				+ "}pre{font-size: 15px;font-family: \"Times New Roman\", Times, serif;}"
				+ "#table2{margin: 0 auto;}tr.spaceUnder > td"
				+ "{padding-left:1em;padding-bottom: 1em;}"
				+ "#body{width:100%;height:100%;background-color:rgb(132, 152, 214);}</style></head>";

		String header = "<div id = \"header\"><h1>Name: " + uc.getName()
				+ "</h1>";
		header += "<h2>ID: " + uc.getID() + "</h2></div>";
		String tableStart = "<div id = \"body\"><table id = \"table2\">";
		String tableEnd = "</table></div>";
		String rowStart = "<tr class=\"spaceUnder\">";
		String rowEnd = "</tr>";
		String description = "<td><h3>Description</h3>" + "<pre>";
		description += uc.getDescription() + "</pre><td>";
		String primaryActors = "<td><h3>Primary Actors</h3>" + "<pre>";
		primaryActors += uc.getPrimString() + "</pre></td>";
		String supActors = "<td><h3>Supporting Actors</h3>" + "<pre>";
		supActors += uc.getSupString() + "</pre></td>";
		String triggers = "<td><h3>Triggers</h3>" + "<pre>";
		triggers += uc.getTriggers() + "</pre></td>";
		String preconditions = "<td><h3>Preconditions</h3>" + "<pre>";
		preconditions += uc.getPreconditions() + "</pre></td>";
		String primFlow = "<td><h3>Primary Flow</h3></td>";
		String altFlow = "<td><h3>Alternative Flow</h3></td>";
		String minG = "<td><h3>Minimal Guarantees</h3>" + "<pre>";
		minG += uc.getMinimalGuaruntees() + "</pre></td>";
		String sucG = "<td><h3>Success Guarantees</h3>" + "<pre>";
		sucG += uc.getSuccessGuarantees() + "</pre></td>";
		html += head + header;
		html += tableStart;
		html += rowStart + description + rowEnd;
		html += rowStart + primaryActors + supActors + rowEnd;
		html += rowStart + triggers + preconditions + rowEnd;
		html += rowStart + primFlow + altFlow + rowEnd;
		html += rowStart + minG + sucG + rowEnd;
		html += tableEnd;
		
		File f = new File(directory);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
