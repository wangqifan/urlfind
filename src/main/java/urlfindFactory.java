import imp.*;

public class urlfindFactory {

    public static urlfind geturlfind(urlfindType type)
    {
          if(type==urlfindType.urlfindimpform3u8)
          {
              return new urlfindimpform3u8();
          }
          else if(type==urlfindType.UrlfindTencent)
          {
              urlfind res= new UrlfindTencent();
              ((UrlfindTencent) res).setparttern(".mp4?sdtfrom");
              return res;
          }
          else if(type==urlfindType.urlfindYoukuEmbed)
          {
              urlfind res= new urlfindYoukuEmbed();
              return res;
          }
          else if(type==urlfindType.urlfindDajiang)
          {
              urlfind res= new urlfindDajiang();
              return res;
          }
          else if(type==urlfindType.UrlfindTencentEmbed)
          {
              urlfind res= new UrlfindTencentEmbed();
              ((UrlfindTencentEmbed) res).setparttern(".mp4?sdtfrom");
              return res;
          }
          else if(type==urlfindType.urlfindXpath)
          {
              urlfind res= new urlfindXpath();
              return res;
          }
          else
          {
              urlfind res= new Urlfindimp();
              ((Urlfindimp) res).setparttern(".mp4?sdtfrom");
              return  res;
          }
    }
}
