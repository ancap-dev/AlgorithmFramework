package ru.ancap.algorithm.walkthrough;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import ru.ancap.algorithm.walkthrough.classic.RegionWalkthroughOperator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WalkthroughTest {

    @Test
    public void testOnePath() {
        Walkthrough<Integer> walkthrough = new Walkthrough<>(
            0,
            (target, previous) -> {
                if (target > 10) return StepResult.DENY;
                else return StepResult.ALLOW;
            },
            new NodeMethodApplier<>() {
                @Override
                public Set<Integer> neighbors(Integer int_) {
                    return Set.of(int_+1);
                }

                @Override
                public boolean equals(Integer one, Integer other) {
                    return one.equals(other);
                }
            }
        );

        WalkthroughResult<Integer> result = walkthrough.walkthrough();

        assertNotNull(result);
        assertEquals(11, result.collected().size(), result.collected().toString());
        assertTrue(result.collected().contains(10), result.collected().toString());
        assertFalse(result.collected().contains(11), result.collected().toString());
    }
    
    @Accessors(fluent = true) @Getter
    @ToString(exclude = {"color", "neighbors"}) @EqualsAndHashCode(exclude = {"color", "neighbors"})
    @RequiredArgsConstructor
    public static class Node {
        
        private final String identity;
        private final NodeColor color;
        private final Set<Node> neighbors;
        
        public Node(String identity) {
            this(identity, Set.of());
        }
        
        public Node(String identity, Set<Node> neighbors) {
            this(identity, NodeColor.BLUE, neighbors);
        }
        
        public Node(String identity, NodeColor color) {
            this(identity, color, Set.of());
        }
        
        public static final NodeMethodApplier<Node> METHOD_APPLIER = new NodeMethodApplier<>() {
            @Override
            public Set<Node> neighbors(Node node) {
                return node.neighbors();
            }

            @Override
            public boolean equals(Node one, Node other) {
                return one.equals(other);
            }
        };
        
    }
    
    public enum NodeColor {
        BLUE,
        RED
    }

    private final Node graph = new Node(
        "base",
        Set.of(
            new Node(
                "left",
                Set.of(
                    new Node("left left"),
                    new Node("left middle", Set.of(new Node("left middle inner", NodeColor.RED))),
                    new Node("left right")
                )
            ),
            new Node("right", Set.of(new Node("right inner", NodeColor.RED, Set.of(new Node("right inner inner")))))
        )
    );

    @Test
    public void testGraph() {
        Node base = this.graph;
        Walkthrough<Node> walkthrough = new Walkthrough<>(
            base,
            (target, previous) -> StepResult.ALLOW,
            Node.METHOD_APPLIER
        );

        WalkthroughResult<Node> result = walkthrough.walkthrough();

        assertNotNull(result);
        assertEquals(9, result.collected().size(), result.collected().toString());
        assertTrue(result.collected().contains(new Node("right inner")), result.collected().toString());
    }

    @Test
    public void testRegion() {
        Node base = this.graph;
        Walkthrough<Node> walkthrough = new Walkthrough<>(
            base,
            new RegionWalkthroughOperator<>(node -> node.color() == NodeColor.BLUE),
            Node.METHOD_APPLIER
        );

        // Act
        WalkthroughResult<Node> result = walkthrough.walkthrough();

        // Assert
        assertNotNull(result);
        assertEquals(6, result.collected().size(), result.collected().toString());
        assertTrue(result.collected().contains(new Node("right")), result.collected().toString());
        assertFalse(result.collected().contains(new Node("left middle inner")), result.collected().toString());
        assertFalse(result.collected().contains(new Node("right inner inner")), result.collected().toString());
    }
    
    
}
