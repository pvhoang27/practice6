
import java.util.ArrayList;
import java.util.List;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;

//[Mã câu hỏi (qCode): vvouxTi5].  Một dịch vụ web (hỗ trợ SOAP version 1.1) được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
//Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
//a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi có thể chứa các từ được phân tách bằng dấu cách hoặc dấu gạch dưới.
//b. Chuyển đổi chuỗi đã nhận được sang ba định dạng khác nhau:
//•	PascalCase: Mỗi từ bắt đầu bằng chữ in hoa, không có khoảng cách giữa các từ.
//•	camelCase: Từ đầu tiên viết thường, các từ tiếp theo viết hoa chữ cái đầu và viết liền nhau.
//•	snake_case: Các từ được viết thường và nối với nhau bằng dấu gạch dưới.
//c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi mảng kết quả chứa ba chuỗi đã định dạng trở lại server, theo thứ tự: PascalCase, camelCase, snake_case.
//Ví dụ: Nếu chuỗi nhận được từ phương thức requestCharacter là "hello world example", các chuỗi kết quả sẽ là:
//•	PascalCase: "HelloWorldExample"
//•	camelCase: "helloWorldExample"
//•	snake_case: "hello_world_example"
//Mảng kết quả sẽ là ["HelloWorldExample", "helloWorldExample", "hello_world_example"], và sẽ được gửi lại server qua phương thức submitCharacter.
//d. Kết thúc chương trình client.
public class ChuyenThanh3Chuoi {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN014",qCode = "vvouxTi5";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService sv = service.getCharacterServicePort();
        String a = sv.requestString(studentCode, qCode).replace(" ", "_").trim().toLowerCase();
        System.out.println(a);
        String [] part = a.split("_");
        String pas= "", cam = "", sna ="";
        List<String> kq  = new ArrayList<>();
        // vong for cho part
        for(int i  = 0 ; i < part.length; i++){
            // nho la part[i] nhe 
            // dau tien la viet hoa chu cai dau roi + voi phan con lai la chu thuong thoi 
            String dauHoa = Character.toUpperCase(part[i].charAt(0)) + part[i].substring(1);
            pas += dauHoa;
            
            if(i==0)  cam+= part[i];
            else cam+= dauHoa;
            
            
        }
        sna = a;
        kq.add(pas);
        kq.add(cam);
        kq.add(sna);
        sv.submitCharacterStringArray(studentCode, qCode, kq);
    }
}
