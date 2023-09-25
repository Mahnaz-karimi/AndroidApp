package com.example.borgerapp;

import static org.mockito.Mockito.verify;

import android.widget.Button;
import android.widget.EditText;

import com.example.borgerapp.Database.Database_SignUp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
class MainActivityTest {
    private MainActivity mainActivity;

    @Mock
    private EditText mockUsername;
    @Mock
    private EditText mockPassword;
    @Mock
    private Button mockLoginButton;
    @Mock
    private Button mockSignupButton;
    @Mock
    private Database_SignUp mockDb;
    @Test // Annotate with @Test to indicate that this is a test method
    void onCreate() {
        // Test logic for onCreate
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mainActivity = new MainActivity();
        mainActivity.username = mockUsername;
        mainActivity.password = mockPassword;
        mainActivity.loginButtom = mockLoginButton;
        mainActivity.signupButton = mockSignupButton;
        mainActivity.db = mockDb;
    }

    @Test
    void onCreate_InitializesUIComponents() {
        mainActivity.onCreate(null);

        // Verify that the UI components have been initialized
        verify(mockUsername).findViewById(R.id.username);
        verify(mockPassword).findViewById(R.id.password);
        verify(mockLoginButton).findViewById(R.id.loginButton);
        verify(mockSignupButton).findViewById(R.id.signupButton);
    }
}