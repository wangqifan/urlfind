import imp.*;

public class urlfindFactory {

    public static urlfind geturlfind(urlfindType type)
    {
          if(type==urlfindType.urlfindimpform3u8)
          {
              return new urlfindimpform3u8();
          }
          else if (type==urlfindType.urlfindyouku)
          {
              return new urlfindyouku();
          }
          else if(type==urlfindType.UrlfindTencent)
          {
              urlfind res= new UrlfindTencent();
              ((UrlfindTencent) res).setparttern(".mp4?sdtfrom");
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
