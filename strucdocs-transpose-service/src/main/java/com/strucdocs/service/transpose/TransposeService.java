package com.strucdocs.service.transpose;

import com.strucdocs.api.chord.Chord;
import com.strucdocs.api.chord.Scale;
import com.strucdocs.api.song.Song;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transpose")
public class TransposeService {

    @RequestMapping(value = "/chord", method = RequestMethod.POST)
    public Chord transposeChord(@RequestBody Chord chord, @RequestParam(value = "key") Integer key) {
        chord.transpose(getScale(key), getSteps(key));
        return chord;
    }

    @RequestMapping(value = "/song", method = RequestMethod.POST)
    public Song transposeSong(@RequestBody Song song, @RequestParam(value = "key") Integer key) {
        song.transpose(getScale(key), getSteps(key));
        return song;
    }

    private int getSteps(@RequestParam(value = "key") Integer key) {
        return key < 0 ? key * -1 : key;
    }

    private Scale getScale(@RequestParam(value = "key") Integer key) {
        return key > 0 ? Scale.Up : Scale.Down;
    }
}
