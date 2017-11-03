package com.pss.booking.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pss.booking.R;
import com.pss.booking.Utils.UserPefer;
import com.pss.booking.activity.MainActivity;
import com.pss.booking.httpManager.NetworkManager;
import com.pss.booking.httpManager.RequestType;
import com.pss.booking.httpManager.ServiceResponseListener;
import com.pss.booking.model.ChatModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.pss.booking.R.drawable.chat;

/**
 * Created by Manu on 12/30/2016.
 */
public class FragmentChat extends Fragment implements ServiceResponseListener {

    private EditText messageET;
    private ListView messagesContainer;
    private Button sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;
    MainActivity mActivity;
    private View rootView;
    NetworkManager networkManager;
    UserPefer mUserPefer;
    ;
    private String otheruser_id = "";
    Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        networkManager = new NetworkManager(mActivity, this);
        mUserPefer = new UserPefer(mActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.updateTitle("CHAT",false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        initControls();
        networkManager.chatBetweenTwo(mUserPefer.getUserId(), otheruser_id);
        // Inflate the layout for this fragment
        timersetup();
        return rootView;
    }

    private void timersetup(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                networkManager.chatBetweenTwo(mUserPefer.getUserId(), otheruser_id);
//Toast.makeText(getActivity(),"Timere",Toast.LENGTH_SHORT).show();
                Log.i("background service", "Service started...");
            }
        }, 0, 10000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Destroy    ","Destroy");
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

    private void initControls() {
        messagesContainer = (ListView) rootView.findViewById(R.id.messagesContainer);
        messageET = (EditText) rootView.findViewById(R.id.messageEdit);
        sendBtn = (Button) rootView.findViewById(R.id.chatSendButton);

        TextView meLabel = (TextView) rootView.findViewById(R.id.meLbl);
        TextView companionLabel = (TextView) rootView.findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) rootView.findViewById(R.id.container);
        companionLabel.setText("My Buddy");


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                displayMessage(chatMessage);
                networkManager.sendingChat(mUserPefer.getUserId(), otheruser_id, messageText);
            }
        });


    }

    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    private void loadDummyHistory(ChatModel chatModel) {

        chatHistory = new ArrayList<ChatMessage>();
        for (int i = 0; i < chatModel.getMessages().size(); i++) {
            ChatMessage msg = new ChatMessage();
            msg.setId(Long.valueOf(chatModel.getMessages().get(i).get_from()));
            if (chatModel.getMessages().get(i).get_from().equals(mUserPefer.getUserId())) {
                msg.setMe(true);
            } else {
                msg.setMe(false);
            }
            msg.setMessage(chatModel.getMessages().get(i).getMessages());
            msg.setDate(chatModel.getMessages().get(i).getTime());
            chatHistory.add(msg);
        }

//        ChatMessage msg1 = new ChatMessage();
//        msg1.setId(2);
//        msg1.setMe(false);
//        msg1.setMessage("How r u doing???");
//        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
//        chatHistory.add(msg1);

        adapter = new ChatAdapter(mActivity, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        for (int i = 0; i < chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }

    }


    @Override
    public void onSuccessResponse(Object responseObject, String responseType) {
        if (responseType.equals(RequestType.SEND_CHATMESSAGE)) {


        } else if (responseType.equals(RequestType.CHAT_BETWEEN)) {
            ChatModel chatModel = (ChatModel) responseObject;
            if(chatModel.getMessages()!=null) {
                for (int i = 0; i < chatModel.getMessages().size(); i++) {
                    Log.e("Actvity", chatModel.getMessages().get(i).getMessages());
                }
                //  Toast.makeText(getActivity(), "test   === >  " + chatModel.getMessages().size(), Toast.LENGTH_SHORT).show();

                loadDummyHistory(chatModel);
            }
        }
    }

    public void getData(Object obj) {
        otheruser_id = (String) obj;


    }
}
