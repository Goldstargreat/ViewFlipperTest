package kr.ac.kopo.viewflippertest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ViewFlipper flipper; // 화면 전환용 객체 저장
    Button btnPrev, btnNext; // 변수 선언(이전 버튼, 다음 버튼)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // 부모 Activity의 onCreate 실행
        EdgeToEdge.enable(this); // 화면을 시스템 바 영역까지 확장
        setContentView(R.layout.activity_main); // activity_main.xml 화면 연결

        ViewCompat.setOnApplyWindowInsetsListener
                (
                // → 시스템 바 정보를 받을 준비가 된 리스너 인터페이스
                findViewById(R.id.main), // id가 main인 View 찾기
                new OnApplyWindowInsetsListener() // 새로운 리스너 객체 생성
                {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets)
                    // 시스템 바 정보가 적용될 때 실행되는 메서드 v → 현재 View, insets → 시스템 바 정보
                    {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        // 상태바/네비게이션 바 크기 가져오기
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        // 시스템 바 크기만큼 여백 추가
                        return insets; // 처리한 insets 반환
                    }
                }
        );

        // 버튼 연결
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        flipper = findViewById(R.id.flipper);

        // 클릭 이벤트 연결
        btnPrev.setOnClickListener(btnListener);
        btnNext.setOnClickListener(btnListener);
    }

    // 클릭 리스너 객체
    View.OnClickListener btnListener = new View.OnClickListener()
        // 버튼 클릭 이벤트 객체 생성
    {
        @Override
        public void onClick(View v)
        // 버튼 클릭 시 실행되는 메서드 , v → 클릭된 버튼
        {
            Button btnEvent = (Button) v;
            if (btnEvent == btnPrev)
            {
                flipper.showPrevious();
            } else
            {
                flipper.showNext();
            }
        }
    };
}