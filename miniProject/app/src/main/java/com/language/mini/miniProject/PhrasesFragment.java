package com.language.mini.miniProject;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {
    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Greetings", "नमस्ते", R.raw.namastay));
        words.add(new Word("Thank you", "धन्येबाद", R.raw.number_five));
        words.add(new Word("I am sorry ", "माफ गर्नु होस् ", R.raw.sorry));
        words.add(new Word("See you again ", "फेरि भेतुअम्ल", R.raw.seeagain));
        words.add(new Word("Tasty", "मिठो छ ", R.raw.taaty));
        words.add(new Word("Where are you going?", "कता जान लागेको?", R.raw.katajana));
        words.add(new Word("What is your name?", "तिम्रो नाम के हो ", R.raw.nammk));
        words.add(new Word("Are you coming?", "तिमि आउछौ ??", R.raw.timi));
        words.add(new Word("I’m coming.", "म आउदै छु ", R.raw.comming));
        words.add(new Word("Let’s go.", "ल जाउ", R.raw.go));
        words.add(new Word("Come here.", "यता आऊ", R.raw.come));
        words.add(new Word("What time is it?", "कति बज्यो ?", R.raw.number_five));
        words.add(new Word("I am here on vacation", "मा एहा छुटी मा छु", R.raw.vacation));
        words.add(new Word("Good Night", "शुभ रात्री", R.raw.goodnight));
        words.add(new Word("Bill please", "बिल लिएर आइदिनुस न ", R.raw.billplease));
        words.add(new Word("At what time it will open? ", "बियो कति बजे खुल्छ?", R.raw.open));
        words.add(new Word("At what time it will close? ", "बियो कति बजे बन्द हुन्छ?", R.raw.close));
        words.add(new Word("Can you help me? ", "मदत गर्न सक्नु हुन्छ?", R.raw.help));
        words.add(new Word("Do you speak English? ", "आइग्रेजी बोल्छौ?", R.raw.eng));
        words.add(new Word("I dont understand ", "मैले बुजिन", R.raw.dont));
        words.add(new Word("How are you? ", "कस्तो छौ?", R.raw.howare));
        words.add(new Word("You are very kind", "तिमि दयालु छौ ", R.raw.kind));
        words.add(new Word("Where I can find Taxi?", "तिएहा ट्याक्सी कता पाउछ?", R.raw.taxi));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
