package Algorithm;






public class Cords
{
  static final double EARTH_RADIUS = 6371000.0D;
  



  static final double NORM_X = 2.0D;
  



  static final double NORM_Y = 2.0D;
  



  static int screen_X = 128;
  static int screen_Y = 64;
  


  public Cords() {}
  


  public static double[] screen_cordes(double[] d_xyz, double yaw, double pitch)
  {
    double[] ans = { -1.0D, -1.0D };
    double[] yaw_pitch = yaw_pitch(d_xyz);
    double d_yaw = yaw_pitch[0] - yaw;
    double d_pitch = yaw_pitch[1] - pitch;
    if (d_yaw >= 180.0D) {
      d_yaw = yaw_pitch[0] - 360.0D - yaw;

    }
    else if (d_yaw <= -180.0D) {
      d_yaw = yaw_pitch[0] - (yaw - 360.0D);
    }
    
    double px = d_yaw * 2.0D;
    double py = d_pitch * 2.0D;
    
    ans[0] = (screen_X / 2 + (int)px);
    ans[1] = (screen_Y / 2 + (int)py);
    return ans;
  }
  






  public static double[] flatWorldDist(double[] ll1, double[] ll2)
  {
    double[] ans = new double[3];
    double dx = ll2[1] - ll1[1];
    double dy = ll2[0] - ll1[0];
    double dz = ll2[2] - ll1[2];
    if (((Math.abs(dx) > 1.0D ? 1 : 0) | (Math.abs(dy) > 1.0D ? 1 : 0)) != 0) return null;
    double x = 6371000.0D * Math.sin(Math.toRadians(dx)) * Math.cos(Math.toRadians(ll1[0]));
    double y = 6371000.0D * Math.sin(Math.toRadians(dy));
    ans[0] = x;ans[1] = y;ans[2] = dz;
    return ans;
  }
  








  public static double[] azmDist(double[] ll1, double[] ll2)
  {
    double[] ans = new double[3];
    double[] vec = flatWorldDist(ll1, ll2);
    double dist = Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1]);
    double ang = angXY(vec[0], vec[1]);
    
    ans[0] = ang;
    ans[1] = dist;
    ans[2] = vec[2];
    return ans;
  }
  






  public static double[] offsetLatLonAlt(double[] ll1, double[] vec)
  {
    double[] ans = new double[3];
    double dz = vec[2];
    if (((Math.abs(vec[0]) > 100000.0D ? 1 : 0) | (Math.abs(vec[1]) > 100000.0D ? 1 : 0)) != 0)
    {
      return null;
    }
    double lon = vec[0] / (6371000.0D * Math.cos(Math.toRadians(ll1[0])));
    double lat = vec[1] / 6371000.0D;
    ll1[1] += Math.toDegrees(lon);ll1[0] += Math.toDegrees(lat);ll1[2] += dz;
    return ans;
  }
  
  public static double[] offsetLatLonAzmDist(double[] ll1, double azm, double dist) { double[] vec = azmDist2cords(azm, dist);
    return offsetLatLonAlt(ll1, vec);
  }
  




  public static double angXY(double dx, double dy)
  {
    double a0 = Math.atan2(dy, dx);
    


    double ans = rad2Deg(a0);
    return ans;
  }
  
  public static double[] yaw_pitch(double[] d_xyz)
  {
    double dx = d_xyz[0];
    double dy = d_xyz[1];
    double dz = d_xyz[2];
    double yaw_rad = Math.atan2(dy, dx);
    double dxy = Math.sqrt(dx * dx + dy * dy);
    double pitch_rad = Math.atan2(dxy, dz);
    double[] ans = new double[2];
    
    ans[0] = rad2Deg(yaw_rad);
    ans[1] = rad2Deg(pitch_rad);
    if (ans[1] >= 180.0D) ans[1] = (360.0D - ans[1]);
    return ans;
  }
  

  public static double[] azmDist2cords(double azm, double dist)
  {
    double a = deg2Rad(azm);
    double[] ans = new double[3];
    ans[0] = (Math.cos(a) * dist);
    ans[1] = (Math.sin(a) * dist);
    ans[2] = 0.0D;
    return ans;
  }
  
  public static double deg2Rad(double deg) { double a = 90.0D - deg;
    if (deg > 270.0D) a = 450.0D - deg;
    a = Math.toRadians(a);
    return a;
  }
  
  public static double rad2Deg(double rad) { double a1 = Math.toDegrees(rad);
    double ans = 90.0D - a1;
    if (a1 > 90.0D) ans = 450.0D - a1;
    return ans;
  }
  
  public static double w2F(double w_width, double f_width)
  {
    double norm = f_width / w_width;
    return norm;
  }
}
