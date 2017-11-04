package jxpl.scnu.curb.immediateInformation.informationDetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jxpl.scnu.curb.R;
import jxpl.scnu.curb.immediateInformation.ImmediateInformation;

import static com.google.common.base.Preconditions.checkNotNull;


public class InformationDetailFragment extends Fragment implements InformationDetailContract.View {


    @BindView(R.id.informationKind)
    ImageView informationKind;
    @BindView(R.id.informationTitle)
    TextView informationTitle;
    @BindView(R.id.info_detail_type)
    TextView infoDetailType;
    @BindView(R.id.info_detail_time)
    TextView infoDetailTime;
    @BindView(R.id.info_detail_content)
    TextView infoDetailContent;
    @BindView(R.id.info_detail_content_url)
    TextView infoDetailContentUrl;
    Unbinder unbinder;
    private InformationDetailContract.Presenter mPresenter;
    private static final String ARGUMUENT_INFO_ID="INFO_ID";


    public InformationDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InformationDetailFragment newInstance(@NonNull String id) {

        Bundle argument=new Bundle();
        argument.putString(ARGUMUENT_INFO_ID,id);
        InformationDetailFragment informationDetailFragment=new InformationDetailFragment();
        informationDetailFragment.setArguments(argument);
        return informationDetailFragment;
    }

    @Override
    public void setPresenter(@NonNull InformationDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showMissingInfo() {
        if(getView()==null)
            return;
        else{
            informationTitle.setText(getString(R.string.missing_info_detail));
            infoDetailContent.setText(getString(R.string.loading_info_error));
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView()==null)
            return;
        if (active){
            informationTitle.setText("");
            infoDetailContent.setText(getString(R.string.loading_info_detail));
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showInfo(@NonNull ImmediateInformation immediateInformation) {
        informationTitle.setText(immediateInformation.getTitle());
        infoDetailTime.setText(immediateInformation.getTime());
        infoDetailType.setText(immediateInformation.getType());
        infoDetailContent.setText(immediateInformation.getContent());
        infoDetailContentUrl.setText(immediateInformation.getContent_url());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
