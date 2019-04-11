package team.dorm301.letterhome.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import team.dorm301.letterhome.R;
import team.dorm301.letterhome.adapter.InboxAdapter;
import team.dorm301.letterhome.base.BaseActivity;
import team.dorm301.letterhome.config.Yunzhi;
import team.dorm301.letterhome.dao.DAOService;
import team.dorm301.letterhome.entity.Letter;
import team.dorm301.letterhome.entity.User;
import team.dorm301.letterhome.service.LetterService;
import team.dorm301.letterhome.util.RecyclerViewUtils;

public class InboxActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inbox_recycler_view)
    RecyclerView inboxRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    LetterService letterService;
    InboxAdapter inboxAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_inbox;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                testLoadLetterList();
            }
        });

        inboxAdapter = new InboxAdapter(this);

        inboxRecyclerView.setAdapter(inboxAdapter);
        RecyclerViewUtils.setDefaultConfig(getApplicationContext(), inboxRecyclerView);

        letterService = Yunzhi.getBean(LetterService.class);

        loadLetterList();
    }

    private void loadLetterList() {
        letterService.getSentLetters()
                .subscribe(new Observer<List<Letter>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Letter> letters) {
                        inboxAdapter.setDataList(letters);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络错误！");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        swipeRefresh.setRefreshing(false);
    }

    private void testLoadLetterList() {
        List<Letter> letterList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Letter letter = new Letter();
            letter.setId((long) i);
            letter.setContent("`内容内容内容内容内容内容内容内容内容内" +
                    "容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
            letter.setSendTime(new Date());
            letter.setSubject("主题主题主题主题主题主题主题");
            User user = new User();
            user.setUsername(DAOService.getInstance().getLogInfo().getUsername());
            letter.setUser(user);
            letter.setRecipient("收件人ＸＸＸ");
            letter.setTarget("地址ＸＸＸＸＸＸＸＸＸ");
            letterList.add(letter);
        }
        inboxAdapter.setDataList(letterList);
        swipeRefresh.setRefreshing(false);
    }
}
