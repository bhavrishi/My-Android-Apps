package com.inclass.uncc.inclass06_801019706;
/*#Assignment: In Class Assignment $
     #   File Name : Table1_InClass06.zip
        #Full Name:  Bhavya Gedi*/
import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rishi on 02/10/17.
 */

public class ReceipeUtil {




    static public class PSAXParser extends DefaultHandler {
         ArrayList<Receipe> rlist;
        Receipe receipe;
        //Wind wind;
        StringBuilder sb;
        public PSAXParser() {
            super();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            rlist = new ArrayList<Receipe>();
            sb = new StringBuilder();

        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("recipe")) {
                receipe = new Receipe();
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName .equals("recipe") ){
                rlist.add(receipe);

            }
            if(localName.equals("title"))
            {
                receipe.setTitle(sb.toString().trim());
            }
            if(localName.equals("ingredients"))
            {
                receipe.setIngredients(sb.toString().trim());
            }
            if(localName.equals("href"))
            {
                receipe.setUrl(sb.toString().trim());
            }
          /*  receipe.setImage(new AsyncImage().execute("http://square.github.io/picasso/"));*/

            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }

        public ArrayList<Receipe> getRlist() {
            return rlist;
        }
        static public ArrayList<Receipe> parseParser(InputStream in) throws IOException, SAXException {

            PSAXParser parser = new PSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);

           return parser.getRlist();



        }


    }
}