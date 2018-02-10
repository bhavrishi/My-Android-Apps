package com.hwork.uncc.periousmid;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rishi on 15/10/17.
 */

class MusicUtil {
    static public class PSAx {


        ArrayList<Music> rlist;
        Music music;
        //Wind wind;
        StringBuilder sb;
        private String tempVal;
     /* //  public PSAXParser() {
            super();
        }*/

        static public ArrayList<Music> parseParser(InputStream in) throws IOException, SAXException, XmlPullParserException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            ArrayList<Music> wlist = new ArrayList<Music>();
            Music weather = null;
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("entry")) {
                            weather = new Music();
                        }
                        if(parser.getName().equals("entry"))    if(parser.getName().equals("link"))
                            weather.setAppurl(parser.getAttributeValue(null, "href"));
                               else if(parser.getName().equals("im:name"))
                                weather.setTitle(parser.nextText().toString());
                         break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("entry")) {
                            wlist.add(weather);
                            weather = null;
                        }
                        break;
                    default:break;
                }
                event = parser.next();
            }


            return wlist;

        }


/*
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            rlist = new ArrayList<Music>();
            sb = new StringBuilder();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        String height1, height2;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            tempVal = "";
         //  if (localName.equals("feed")) *//*{

                    if (localName.equals("entry"))
                        music = new Music();
                *//*}


            }*//*
     // if(localName.equals("feed"))
            if (localName.equals("entry"))
                    if (localName .equals( "link"))
               music.setId(attributes.getValue("href"));
           *//*
           if (localName == "im:price") {
               music.setId(attributes.getValue("amount"));
           }
       *//*    *//* if (localName.equals("entry"))
           if (localName .equals("link")) {
               music.setAppurl(attributes.getValue("href"));
           }*//*
        *//*   if (localName == "im:image") {
               height1=attributes.getValue("href");
           }*//*

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
         // if (localName.equals("feed"))
                   if (localName.equals("entry"))
                        rlist.add(music);
              //  }


            //}
  *//*      if (localName.equals("entry"))
                if (localName.equals("title"))
                    music.setTitle(tempVal);
*//*
          *//* if(localName.equals("artist"))
           {
               music.setDevname(sb.toString().trim());
           }*//*
           *//*if(localName.equals("href"))
           {
               receipe.setUrl(sb.toString().trim());
           }*//*
          *//*  receipe.setImage(new AsyncImage().execute("http://square.github.io/picasso/"));*//*

            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            *//*sb.append(ch, start, length);*//*
            tempVal = new String(ch, start, length);
        }

        public ArrayList<Music> getRlist() {
            return rlist;
        }

        static public ArrayList<Music> parseParser(InputStream in) throws IOException, SAXException {
            PSAXParser parser = new PSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);

            return parser.getRlist();


        }*/
    }
}
