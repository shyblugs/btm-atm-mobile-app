package com.ideofuzion.btm.main.scanqr;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.ideofuzion.btm.BTMApplication;
import com.ideofuzion.btm.R;
import com.ideofuzion.btm.main.enteramount.EnterAmountActivity;
import com.ideofuzion.btm.model.QRModel;
import com.ideofuzion.btm.permission.PermissionActivity;
import com.ideofuzion.btm.utils.AlertMessage;
import com.ideofuzion.btm.utils.DialogHelper;
import com.ideofuzion.btm.utils.Fonts;
import com.ideofuzion.btm.utils.PermissionHandler;

import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by khali on 6/5/2017.
 */

public class ScanQRActivity extends Activity implements ZXingScannerView.ResultHandler {
    private static final int CAMERA_PERMISSION_ID = 1;
    //my resources
    private Typeface fontRegular;
    private Typeface fontSemiBold;
    private Typeface fontBold;
    private boolean flashStatus;
    TextView text_scanQRFragment_dollarRate, text_scanQRFragment_title,
            text_scanQRFragment_description, text_scanQRFragment_scanning;
    Button button_scanQRFragment_cancel;
    LinearLayout linearLayout_scanQRFragment_scannerContainer;
    private ZXingScannerView mScannerView;
    DialogHelper dialogHelper;
    PermissionHandler permissionHandler;

    QRModel qrModel = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_scan_qr);
            initResources();

            iniTypeface();

            addListener();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        } catch (Exception e) {
        }
    }

    private void addListener() {
        button_scanQRFragment_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ((MainActivity) getActivity()).replaceFragment(((MainActivity) getActivity()).enterAmountFragment);
                finish();

            }
        });
    }

    private void iniTypeface() {
        text_scanQRFragment_dollarRate.setTypeface(fontSemiBold);
        text_scanQRFragment_title.setTypeface(fontBold);
        text_scanQRFragment_description.setTypeface(fontRegular);
        text_scanQRFragment_scanning.setTypeface(fontRegular);
        button_scanQRFragment_cancel.setTypeface(fontRegular);
    }

    private void initResources() {

        if (getIntent().hasExtra("dollarRate")) {
            BTMApplication.getInstance().getBTMUserObj().setBitcoinDollarRate(getIntent().getStringExtra("dollarRate"));
        }
        permissionHandler = new PermissionHandler(this);
        dialogHelper = new DialogHelper(this);

        //initializing TypeFaces objects
        fontRegular = Fonts.getInstance(getApplicationContext()).getTypefaceRegular();
        fontSemiBold = Fonts.getInstance(getApplicationContext()).getTypefaceSemiBold();
        fontBold = Fonts.getInstance(getApplicationContext()).getTypefaceBold();

        text_scanQRFragment_dollarRate = (TextView) findViewById(R.id.text_scanQRFragment_dollarRate);
        text_scanQRFragment_title = (TextView) findViewById(R.id.text_scanQRFragment_title);
        text_scanQRFragment_title.setText(Html.fromHtml("<i>Scan QR Code</i>"));
        text_scanQRFragment_description = (TextView) findViewById(R.id.text_scanQRFragment_description);
        text_scanQRFragment_scanning = (TextView) findViewById(R.id.text_scanQRFragment_scanning);
        text_scanQRFragment_scanning.setText(Html.fromHtml("<i>Scanning...</i>"));

        button_scanQRFragment_cancel = (Button) findViewById(R.id.button_scanQRFragment_cancel);
        linearLayout_scanQRFragment_scannerContainer = (LinearLayout) findViewById(R.id.linearLayout_scanQRFragment_scannerContainer);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view

        linearLayout_scanQRFragment_scannerContainer.addView(mScannerView);


        //init data
        text_scanQRFragment_dollarRate.setText("1 BTC = " + BTMApplication.getInstance().getBTMUserObj().getBitcoinDollarRate() + " USD");

    }

    @Override
    public void onResume() {
        super.onResume();
        if (permissionHandler.isPermissionAvailable(Manifest.permission.CAMERA))
            startCamera();
        else
        {
            permissionHandler.requestPermission(Manifest.permission.CAMERA);
        }
    }

    private void startCamera() {
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(CameraSource.CAMERA_FACING_FRONT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_ID && grantResults.length > 0 && grantResults[0] > -1) {
            startCamera();
        } else {
            Intent intent = new Intent(this, PermissionActivity.class);
            intent.putExtra(PermissionActivity.PARAM_PERMISSION_CAMERA, true);
            startActivity(intent);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        /*Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).initResources();*/

        String data = "{" + rawResult.getText() + "}";

        if (data != null) {
            Gson gson = new Gson();

            try {
                qrModel = gson.fromJson(data, QRModel.class);
                BTMApplication.getInstance().setQrModel(qrModel);
                startActivity(new Intent(ScanQRActivity.this, EnterAmountActivity.class));

            } catch (Exception e) {
                qrModel = null;
                AlertMessage.showError(text_scanQRFragment_description, "No appropriate data found");
                mScannerView.resumeCameraPreview(this);
            }
        }
    }

 /*            mScannerView.resumeCameraPreview(this);
*/


    private static class CustomViewFinderView extends ViewFinderView {
        public static final String TRADE_MARK_TEXT = "";
        public static final int TRADE_MARK_TEXT_SIZE_SP = 40;
        public final Paint PAINT = new Paint();

        public CustomViewFinderView(Context context) {
            super(context);
            init();
        }

        public CustomViewFinderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            PAINT.setColor(Color.WHITE);
            PAINT.setAntiAlias(true);
            float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    TRADE_MARK_TEXT_SIZE_SP, getResources().getDisplayMetrics());
            PAINT.setTextSize(textPixelSize);
            setSquareViewFinder(true);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawTradeMark(canvas);
        }

        private void drawTradeMark(Canvas canvas) {
            Rect framingRect = getFramingRect();
            float tradeMarkTop;
            float tradeMarkLeft;
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.getTextSize() + 10;
                tradeMarkLeft = framingRect.left;
            } else {
                tradeMarkTop = 10;
                tradeMarkLeft = canvas.getHeight() - PAINT.getTextSize() - 10;
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }
}
