import java.util.ArrayList;
int min = 10;
ArrayList<PVector[]> polyList = new ArrayList();
int w = 800;
int h = 800;
boolean flag = true;

void setup(){
  polyList.add(new PVector[]{new PVector(100, 100), new PVector(700, 100), new PVector(100, 700), new PVector(700, 700)});
  println(getArea(polyList.get(0)));
  size(800, 800);
  background(255);
  frameRate(5);
}

void draw(){
  background(255);
  for(int i = 0; i < polyList.size(); i++){
    PVector[] poly = polyList.get(i);
    beginShape();
    vertex(poly[0].x, poly[0].y);
    vertex(poly[1].x, poly[1].y);
    vertex(poly[0].x, poly[0].y);
    vertex(poly[2].x, poly[2].y);
    vertex(poly[3].x, poly[3].y);
    vertex(poly[1].x, poly[1].y);
    endShape(CLOSE);
    if(flag){
      split(poly);
    }
  }
}

void printPoly(PVector[] poly){
  if(poly.length != 4){
     return;
  }
  PVector a = poly[0];
  PVector b = poly[1];
  PVector c = poly[2];
  PVector d = poly[3];
  println();
  print(a);
  print(b);
  print(c);
  print(d);
  println();
}

//四边形分割
void split(PVector[] poly){
  if(poly.length != 4){
     return;
  }
  PVector a = poly[0];
  PVector b = poly[1];
  PVector c = poly[2];
  PVector d = poly[3];
  double ab = Math.sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
  double ac = Math.sqrt(pow(a.x - c.x, 2) + pow(a.y - c.y, 2));
  double bd = Math.sqrt(pow(d.x - b.x, 2) + pow(d.y - b.y, 2));
  double cd = Math.sqrt(pow(c.x - d.x, 2) + pow(c.y - d.y, 2));
  double max = Math.max(Math.max(Math.max(ab, ac), bd), cd);
  PVector x1;
  PVector x2;
  PVector[] p1;
  PVector[] p2;
  double rand1 = random(1.0/3.0, 2.0/3.0);
  double rand2 = random(1.0/3.0, 2.0/3.0);
  //double rand1 = 0.5;
  //double rand2 = 0.5;
  if(ab == max || cd == max){
    x1 = getPoint(a, b, rand1);
    x2 = getPoint(c, d, rand2);
    print(x1);
    print(x2);
    p1 = new PVector[]{a, x1, c, x2};
    p2 = new PVector[]{x1, b, x2, d};
  }else{
    x1 = getPoint(a, c, rand1);
    x2 = getPoint(b, d, rand1);
    p1 = new PVector[]{a, b, x1, x2};
    p2 = new PVector[]{x1, x2, c, d};
  }
  if(getArea(p1) <= min || getArea(p1) <= min){
    flag = false;
  }
  polyList.add(p1);
  polyList.add(p2);
}

PVector getPoint(PVector a, PVector b, double c){
  double x = Math.abs(a.x - b.x) * c;
  double y = Math.abs(a.y - b.y) * c;
  //println("xy", x, y, a, b , c);
  if(a.x <= b.x){
    x += a.x;
  }else{
    x = a.x - x;
  }
  
  if(a.y <= b.y){
    y += a.y;
  }else{
    y = a.y - y;
  }
  return new PVector((int)x, (int)y);
}

//求三角形面积
double getTriangleArea(PVector a, PVector b, PVector c){
  double area = 0.0;
  double ab = Math.sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
  double ac = Math.sqrt(pow(a.x - c.x, 2) + pow(a.y - c.y, 2));
  double cb = Math.sqrt(pow(c.x - b.x, 2) + pow(c.y - b.y, 2));
  // 不能构成三角形;
  if(ab + ac <= cb || ab + cb <= ac || ac + cb <= ab){
    return area;
  }
  // 利用海伦公式。s = sqr(p * (p - a)(p - b)(p - c));
  double p = (ab + ac + cb) / 2;  // 半周长
  area = Math.sqrt(p * (p - ab) * (p - ac) * (p - cb)) ;
  return area;
}

//求四边形面积
double getArea(PVector[] poly){
    if(poly.length != 4){
      return 0.0;
    }
    PVector a = poly[0];
    PVector b = poly[1];
    PVector c = poly[2];
    PVector d = poly[3];
    // 对于凸四边形，以任一点分割成两个三角形均可
    // 1，以ac线分割
    double area11 = getTriangleArea(a, b, c);
    double area12 = getTriangleArea(a, d, c);
    double area1 = area11 + area12;
    // 2，以bd线分割
    double area21 = getTriangleArea(b, a, d);
    double area22 = getTriangleArea(b, c, d);
    double area2 = area21 + area22;

   // 对于凸四边形，以任一点分割成两个三角形均可；对于凹四边形，取分割面积小的一种算法为实际面积
    return Math.min(area1,area2);
}
